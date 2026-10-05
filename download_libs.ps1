$urls = @(
    'https://repo1.maven.org/maven2/org/apache/poi/poi/5.2.3/poi-5.2.3.jar',
    'https://repo1.maven.org/maven2/org/apache/poi/poi-ooxml/5.2.3/poi-ooxml-5.2.3.jar',
    'https://repo1.maven.org/maven2/org/apache/poi/poi-ooxml-lite/5.2.3/poi-ooxml-lite-5.2.3.jar',
    'https://repo1.maven.org/maven2/org/apache/xmlbeans/xmlbeans/5.1.1/xmlbeans-5.1.1.jar',
    'https://repo1.maven.org/maven2/org/apache/commons/commons-compress/1.21/commons-compress-1.21.jar',
    'https://repo1.maven.org/maven2/org/apache/commons/commons-collections4/4.4/commons-collections4-4.4.jar',
    'https://repo1.maven.org/maven2/commons-io/commons-io/2.11.0/commons-io-2.11.0.jar',
    'https://repo1.maven.org/maven2/org/apache/logging/log4j/log4j-api/2.18.0/log4j-api-2.18.0.jar',
    'https://repo1.maven.org/maven2/com/zaxxer/SparseBitSet/1.2/SparseBitSet-1.2.jar'
)

if (-not (Test-Path -Path 'lib')) {
    New-Item -ItemType Directory -Path 'lib' | Out-Null
}

foreach ($url in $urls) {
    $fileName = $url.Substring($url.LastIndexOf('/') + 1)
    $dest = "lib/$fileName"
    Write-Host "Downloading $fileName ..."
    Invoke-WebRequest -Uri $url -OutFile $dest
}
Write-Host "All libraries downloaded successfully."
