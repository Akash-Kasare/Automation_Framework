# SauceDemo Automation Project - GitHub Upload Script
# This script automates the setup and upload of your project to GitHub
# Run this script in PowerShell with Administrator privileges

param(
    [string]$GitHubUsername = "",
    [string]$RepositoryName = "SauceDemo_Automation_Cucumber",
    [string]$ProjectPath = "C:\Users\akash\eclipse-workspace\SauceDemo_Automation_Cucmber"
)

# Color output
function Write-Success {
    param([string]$Message)
    Write-Host "✓ $Message" -ForegroundColor Green
}

function Write-Error {
    param([string]$Message)
    Write-Host "✗ $Message" -ForegroundColor Red
}

function Write-Info {
    param([string]$Message)
    Write-Host "ℹ $Message" -ForegroundColor Cyan
}

function Write-Warning {
    param([string]$Message)
    Write-Host "⚠ $Message" -ForegroundColor Yellow
}

# Main script
Clear-Host
Write-Host "╔════════════════════════════════════════════════════════════╗" -ForegroundColor Cyan
Write-Host "║  SauceDemo Automation Project - GitHub Upload Script       ║" -ForegroundColor Cyan
Write-Host "╚════════════════════════════════════════════════════════════╝" -ForegroundColor Cyan

Write-Host ""
Write-Warning "IMPORTANT: Make sure you have:"
Write-Warning "1. Created a NEW repository on GitHub"
Write-Warning "2. Your GitHub username ready"
Write-Warning "3. Personal Access Token or SSH key configured"
Write-Host ""

# Get GitHub username if not provided
if (-not $GitHubUsername) {
    $GitHubUsername = Read-Host "Enter your GitHub username"
    if ([string]::IsNullOrWhiteSpace($GitHubUsername)) {
        Write-Error "GitHub username is required!"
        exit 1
    }
}

Write-Info "Using repository: https://github.com/$GitHubUsername/$RepositoryName"
Write-Info "Project path: $ProjectPath"
Write-Host ""

# Check if project path exists
if (-not (Test-Path $ProjectPath)) {
    Write-Error "Project path does not exist: $ProjectPath"
    exit 1
}

Write-Success "Project path found"

# Navigate to project
Set-Location $ProjectPath
Write-Success "Navigated to project directory"

# Check if git is installed
try {
    $gitVersion = git --version
    Write-Success "Git installed: $gitVersion"
} catch {
    Write-Error "Git is not installed or not in PATH"
    exit 1
}

Write-Host ""
Write-Info "═══ Step 1: Configure Git User ═══"
$userName = Read-Host "Enter your Git name (or press Enter to skip if already configured)"
if (-not [string]::IsNullOrWhiteSpace($userName)) {
    git config --global user.name $userName
    Write-Success "Git user name configured: $userName"
}

$userEmail = Read-Host "Enter your Git email (or press Enter to skip if already configured)"
if (-not [string]::IsNullOrWhiteSpace($userEmail)) {
    git config --global user.email $userEmail
    Write-Success "Git email configured: $userEmail"
}

Write-Host ""
Write-Info "═══ Step 2: Initialize Local Repository ═══"
if (Test-Path ".git") {
    Write-Warning "Git repository already initialized"
} else {
    git init
    Write-Success "Git repository initialized"
}

Write-Host ""
Write-Info "═══ Step 3: Configure .gitignore ═══"
if (Test-Path ".gitignore") {
    Write-Success ".gitignore already exists"
} else {
    Write-Warning ".gitignore not found. Creating default..."
    $gitignore = @"
# Build artifacts
target/
*.class
*.jar

# IDE
.idea/
.vscode/
*.iml
*.iws
*.ipr

# OS
.DS_Store
Thumbs.db

# Maven
.mvn/
*.log

# Logs
logs/
"@
    $gitignore | Set-Content ".gitignore"
    Write-Success ".gitignore created"
}

Write-Host ""
Write-Info "═══ Step 4: Stage All Files ═══"
git add .
Write-Success "All files staged"

Write-Host ""
Write-Info "═══ Step 5: Review Staged Files ═══"
git status
Write-Host ""
$confirm = Read-Host "Does everything look correct? (yes/no)"
if ($confirm -ne "yes") {
    Write-Error "Upload cancelled"
    exit 1
}

Write-Host ""
Write-Info "═══ Step 6: Create Initial Commit ═══"
$commitMessage = Read-Host "Enter commit message (or press Enter for default)"
if ([string]::IsNullOrWhiteSpace($commitMessage)) {
    $commitMessage = "Initial commit: SauceDemo Automation Framework with Cucumber, Selenium, and ExtentReports"
}
git commit -m $commitMessage
Write-Success "Initial commit created"

Write-Host ""
Write-Info "═══ Step 7: Add Remote Repository ═══"
$remoteUrl = "https://github.com/$GitHubUsername/$RepositoryName.git"
Write-Info "Remote URL: $remoteUrl"

# Check if remote already exists
$remoteCheck = git remote
if ($remoteCheck -contains "origin") {
    Write-Warning "Remote 'origin' already exists"
    git remote remove origin
    Write-Info "Removed existing remote"
}

git remote add origin $remoteUrl
Write-Success "Remote repository added"

Write-Host ""
Write-Info "═══ Step 8: Rename Branch to Main ═══"
git branch -M main
Write-Success "Branch renamed to 'main'"

Write-Host ""
Write-Info "═══ Step 9: Push to GitHub ═══"
Write-Warning "You will be prompted to authenticate"
Write-Warning "For HTTPS: Use your GitHub username and Personal Access Token"
Write-Warning "For SSH: Make sure your SSH key is configured"
Write-Host ""

$confirm = Read-Host "Ready to push to GitHub? (yes/no)"
if ($confirm -eq "yes") {
    try {
        git push -u origin main
        Write-Success "Project successfully pushed to GitHub!"
        Write-Success "Repository URL: https://github.com/$GitHubUsername/$RepositoryName"
    } catch {
        Write-Error "Failed to push to GitHub"
        Write-Error "Error: $_"
        Write-Info "Please check your authentication and try again"
        exit 1
    }
} else {
    Write-Warning "Push cancelled. You can push manually later with:"
    Write-Host "git push -u origin main" -ForegroundColor Yellow
}

Write-Host ""
Write-Host "╔════════════════════════════════════════════════════════════╗" -ForegroundColor Green
Write-Host "║  Upload Process Complete!                                  ║" -ForegroundColor Green
Write-Host "╚════════════════════════════════════════════════════════════╝" -ForegroundColor Green

Write-Host ""
Write-Info "Next steps:"
Write-Host "1. Visit https://github.com/$GitHubUsername/$RepositoryName" -ForegroundColor Yellow
Write-Host "2. Configure repository settings (optional)"
Write-Host "3. Add GitHub Actions for CI/CD (optional)"
Write-Host "4. Share the repository link with your team"
Write-Host ""
Write-Info "For future updates, use:"
Write-Host "git add ." -ForegroundColor Yellow
Write-Host "git commit -m 'Your message'" -ForegroundColor Yellow
Write-Host "git push origin main" -ForegroundColor Yellow

