# PowerShell script to test the post creation endpoint

$uri = "http://localhost:8080/core"
$body = @{
    content = "Test post content"
} | ConvertTo-Json

Write-Host "Sending POST request to $uri with body: $body"

try {
    $response = Invoke-RestMethod -Uri $uri -Method Post -Body $body -ContentType "application/json"
    Write-Host "Response received:"
    $response | ConvertTo-Json
    Write-Host "Post creation successful!"
} catch {
    Write-Host "Error occurred:"
    Write-Host $_.Exception.Message
    if ($_.Exception.Response) {
        $responseBody = $_.Exception.Response.GetResponseStream()
        $reader = New-Object System.IO.StreamReader($responseBody)
        $reader.BaseStream.Position = 0
        $reader.DiscardBufferedData()
        $responseBody = $reader.ReadToEnd()
        Write-Host "Response body: $responseBody"
    }
}