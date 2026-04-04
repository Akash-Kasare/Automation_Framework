# GitHub Upload - Quick Checklist & Setup

## Pre-Upload Checklist

- [ ] GitHub account created and logged in
- [ ] New repository created on GitHub
- [ ] Git installed on your machine (`git --version`)
- [ ] GitHub username ready
- [ ] Authentication method chosen (HTTPS with PAT or SSH)

---

## Quick Command-Line Upload (No Script)

### Step 1: Configure Git (First Time Only)
```powershell
git config --global user.name "Your Name"
git config --global user.email "your.email@example.com"
```

### Step 2: Navigate to Project
```powershell
cd C:\Users\akash\eclipse-workspace\SauceDemo_Automation_Cucmber
```

### Step 3: Initialize Repository
```powershell
git init
```

### Step 4: Add All Files
```powershell
git add .
```

### Step 5: Create Commit
```powershell
git commit -m "Initial commit: SauceDemo Automation Framework"
```

### Step 6: Add Remote
```powershell
git remote add origin https://github.com/YOUR_USERNAME/SauceDemo_Automation_Cucumber.git
```

### Step 7: Rename Branch
```powershell
git branch -M main
```

### Step 8: Push to GitHub
```powershell
git push -u origin main
```

---

## Using the Automated Script

If you prefer the interactive script:

```powershell
# Navigate to project
cd C:\Users\akash\eclipse-workspace\SauceDemo_Automation_Cucmber

# Run the script
.\upload-to-github.ps1
```

**If script execution is blocked:**
```powershell
Set-ExecutionPolicy -ExecutionPolicy RemoteSigned -Scope CurrentUser
.\upload-to-github.ps1
```

---

## What Each File Does

| File | Purpose |
|------|---------|
| `GITHUB_SETUP_GUIDE.md` | Detailed step-by-step guide |
| `upload-to-github.ps1` | Interactive PowerShell script for automated setup |
| This checklist | Quick reference for commands |

---

## GitHub Repository Settings (After Upload)

### Add Repository Description
1. Go to repository → Settings → General
2. Add description: "Selenium WebDriver automation testing framework using Cucumber BDD for SauceDemo website"
3. Add website (optional)

### Add GitHub Topics (Tags)
1. Go to repository → About (right side)
2. Click gear icon
3. Add topics:
   - `automation`
   - `cucumber`
   - `bdd`
   - `selenium`
   - `testing`
   - `java`

### Configure Branch Protection (Optional)
1. Go to Settings → Branches
2. Add rule for `main` branch
3. Require pull request reviews before merging

### Enable GitHub Pages (Optional - for reports)
1. Go to Settings → Pages
2. Select `main` branch
3. Select `/docs` folder (if available)

---

## Troubleshooting

### Authentication Failed
**Problem:** `fatal: Authentication failed for 'https://github.com/...`

**Solution:**
- For HTTPS: Use Personal Access Token (not password)
- For SSH: Ensure SSH key is added to GitHub

### Remote Already Exists
**Problem:** `fatal: remote origin already exists`

**Solution:**
```powershell
git remote remove origin
git remote add origin https://github.com/YOUR_USERNAME/SauceDemo_Automation_Cucumber.git
```

### Can't Find Command
**Problem:** `git: command not found`

**Solution:**
- Install Git from https://git-scm.com
- Restart PowerShell/Command Prompt
- Verify: `git --version`

### Large Files Being Rejected
**Problem:** `error: File is too large`

**Solution:**
- Your `.gitignore` excludes large files (target/, etc.)
- These won't be uploaded (which is correct)

---

## Post-Upload: Future Workflow

```powershell
# Make changes to your code...

# Stage changes
git add .

# Commit
git commit -m "Description of changes"

# Push
git push origin main
```

---

## Verify Upload Success

1. **Visit your repository:**
   ```
   https://github.com/YOUR_USERNAME/SauceDemo_Automation_Cucumber
   ```

2. **Check these are present:**
   - ✅ `pom.xml`
   - ✅ `src/` directory
   - ✅ `DOCUMENTATION/` directory
   - ✅ `README.md`
   - ✅ `.gitignore`

3. **Check these are NOT present:**
   - ✅ No `target/` folder (excluded)
   - ✅ No `.idea/` folder (excluded)
   - ✅ No compiled `.class` files (excluded)

---

## Additional Setup (Optional)

### Add CI/CD Pipeline
Create `.github/workflows/maven.yml`:
```yaml
name: Build and Test

on: [push, pull_request]

jobs:
  build:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v3
      - uses: actions/setup-java@v3
        with:
          java-version: '21'
      - run: mvn clean test
```

### Add README Badge
Add to your `README.md`:
```markdown
[![Build Status](https://github.com/YOUR_USERNAME/SauceDemo_Automation_Cucumber/workflows/Build%20and%20Test/badge.svg)](https://github.com/YOUR_USERNAME/SauceDemo_Automation_Cucumber/actions)
```

---

## Contact & Support

- **Git Help:** `git help <command>`
- **GitHub Docs:** https://docs.github.com
- **Git Documentation:** https://git-scm.com/doc

---

**Created:** April 4, 2026  
**Last Updated:** April 4, 2026

