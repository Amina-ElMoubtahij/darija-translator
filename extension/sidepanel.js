document.addEventListener("DOMContentLoaded", () => {
  const textArea = document.getElementById("text");
  const sendBtn = document.getElementById("sendBtn");
  const result = document.getElementById("result");
  const clearIcon = document.getElementById("clearIcon");

  // 1) Lire au démarrage
  chrome.storage.local.get("lastSelectedText", (data) => {
    if (data.lastSelectedText) {
      textArea.value = data.lastSelectedText;
      clearIcon.style.display = "block"; // afficher clear si texte existe
    }
  });

  // 2) Mettre à jour automatiquement quand selection change
  chrome.storage.onChanged.addListener((changes, area) => {
    if (area === "local" && changes.lastSelectedText) {

      // CORRECTION : vérifier si newValue existe
      const newText = changes.lastSelectedText.newValue;

      if (newText !== undefined && newText !== null) {
        textArea.value = newText;
        clearIcon.style.display = "block";
      } else {
        textArea.value = "";
        clearIcon.style.display = "none";
      }
    }
  });

  // 3) Traduction
  sendBtn.addEventListener("click", () => {
    const text = textArea.value.trim();
    if (!text) return;

    result.innerText = "Translating...";

    fetch("http://localhost:8080/Translation/rest/translate", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        "Authorization": "Basic " + btoa("user:password")
      },
      body: JSON.stringify({ text: text })
    })
    .then(res => res.json())
    .then(data => {
      result.innerText = data.translatedText;
    })
    .catch(err => {
      result.innerText = "Error: " + err.message;
    });
  });

  // Afficher "clear" seulement si le champ n'est pas vide
  textArea.addEventListener("input", () => {
    clearIcon.style.display = textArea.value.trim() ? "block" : "none";
  });

  // Clear quand on clique sur "clear"
  clearIcon.addEventListener("click", () => {
    textArea.value = "";
    result.innerText = "";
    chrome.storage.local.remove("lastSelectedText");
    clearIcon.style.display = "none";
  });
});
