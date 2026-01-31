chrome.runtime.onMessage.addListener((message, sender, sendResponse) => {
  if (message.type === "TEXT_SELECTED") {
    chrome.storage.local.set({ lastSelectedText: message.text });
  }
});
