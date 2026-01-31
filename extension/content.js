function captureSelection() {
  const selection = window.getSelection();
  if (!selection) return;

  const text = selection.toString().trim();
  if (!text) return;

  chrome.runtime.sendMessage({
    type: "TEXT_SELECTED",
    text: text
  });
}

document.addEventListener("mouseup", () => {
  setTimeout(captureSelection, 100);
});

document.addEventListener("keyup", () => {
  setTimeout(captureSelection, 100);
});
