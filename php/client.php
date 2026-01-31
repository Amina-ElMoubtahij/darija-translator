<?php
error_reporting(E_ALL);
ini_set('display_errors', 1);

$user = "user";
$pass = "password";
$api = "http://localhost:8080/Translation/rest/translate";

$out = "";
$textValue = "";

if ($_SERVER["REQUEST_METHOD"] === "POST") {
  $textValue = trim($_POST["text"] ?? "");

  if ($textValue !== "") {
    $payload = json_encode(["text" => $textValue], JSON_UNESCAPED_UNICODE);
    $auth = base64_encode("$user:$pass");

    $opts = [
      "http" => [
        "method"  => "POST",
        "header"  => "Content-Type: application/json\r\n" .
                     "Authorization: Basic $auth\r\n",
        "content" => $payload,
        "timeout" => 20
      ]
    ];

    $ctx = stream_context_create($opts);
    $resp = @file_get_contents($api, false, $ctx);

    if ($resp === false) {
      $out = "Request failed. Check backend URL / server running.";
    } else {
      $json = json_decode($resp, true);
      $out = $json["translatedText"] ?? $resp;
    }
  } else {
    $out = "Please type text.";
  }
}
?>
<!doctype html>
<html>
<head>
  <meta charset="utf-8">
  <title>Darija Translator</title>
  <link rel="stylesheet" href="style.css">
</head>
<body>
  <div class="container">
    <h1>English → Darija</h1>

    <form method="post" class="form">
      <label for="text">English text</label>
      <textarea id="text" name="text" rows="6" placeholder="Type English text..."><?= htmlspecialchars($textValue) ?></textarea>

      <button type="submit" class="btn">Translate</button>
    </form>

    <div class="result">
      <label>Result (Darija)</label>
      <pre><?= htmlspecialchars($out) ?></pre>
    </div>
  </div>
</body>
</html>
