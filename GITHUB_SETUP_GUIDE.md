# GitHub Repository Setup Guide - SauceDemo Automation Project

## Overview
This guide will help you upload your SauceDemo Automation project to a new GitHub repository for the first time.

---

## Step-by-Step Instructions

### STEP 1: Create a New Repository on GitHub

1. **Go to GitHub** - Visit https://github.com and sign in to your account
2. **Create New Repo** - Click the `+` icon (top-right) → Select "New repository"
3. **Configure Repository:**
   - **Repository name:** `SauceDemo_Automation_Cucumber` (or your preferred name)
   - **Description:** `Selenium WebDriver automation testing framework using Cucumber BDD for SauceDemo website`
   - **Visibility:** Choose "Public" or "Private" as per your preference
   - **Initialize repository:** 
     - ✅ **DO NOT** check "Add a README file" (we already have one)
     - ✅ **DO NOT** check "Add .gitignore" (we already have one)
     - ✅ **DO NOT** select a license (optional)
4. **Create Repository** - Click "Create repository"

After creation, GitHub will show you commands to push an existing repository.

---

### STEP 2: Configure Git Locally (First Time Setup)

Open PowerShell/Command Prompt and configure your Git credentials:

```powershell
git config --global user.name "Your Name"
git config --global user.email "your.email@example.com"
```

**Example:**
```powershell
git config --global user.name "Akash"
git config --global user.email "akash@example.com"
```

**Verify Configuration:**
```powershell
git config --global --list
```

---

### STEP 3: Initialize Local Repository

Navigate to your project folder and initialize Git:

```powershell
cd C:\Users\akash\eclipse-workspace\SauceDemo_Automation_Cucmber
git init
```

---

### STEP 4: Add Project Files to Git

Add all project files to the staging area:

```powershell
git add .
```

**Verify files are staged:**
```powershell
git status
```

---

### STEP 5: Create Initial Commit

Create your first commit:

```powershell
git commit -m "Initial commit: SauceDemo Automation Framework with Cucumber, Selenium, and ExtentReports"
```

---

### STEP 6: Set Remote Repository

Add your GitHub repository as the remote:

```powershell
git remote add origin https://github.com/YOUR_USERNAME/SauceDemo_Automation_Cucumber.git
```

**Replace:**
- `YOUR_USERNAME` - Your actual GitHub username

**Verify the remote is set:**
```powershell
git remote -v
```

Should show:
```
origin  https://github.com/YOUR_USERNAME/SauceDemo_Automation_Cucumber.git (fetch)
origin  https://github.com/YOUR_USERNAME/SauceDemo_Automation_Cucumber.git (push)
```

---

### STEP 7: Rename Branch (if needed)

If the default branch is "master", rename it to "main":

```powershell
git branch -M main
```

---

### STEP 8: Push to GitHub

Upload your project to GitHub:

```powershell
git push -u origin main
```

**First time note:** 
- You may be prompted to authenticate
- If using HTTPS, enter your GitHub username and Personal Access Token (PAT)
- If using SSH, ensure your SSH key is configured

---

## Authentication Options

### Option A: HTTPS with Personal Access Token (Recommended)

1. Go to GitHub Settings → Developer settings → Personal access tokens → Tokens (classic)
2. Click "Generate new token (classic)"
3. Select scopes: `repo` (Full control of private repositories)
4. Generate and copy the token
5. When Git prompts for password, paste the token

### Option B: SSH Key Authentication

1. **Generate SSH Key:**
   ```powershell
   ssh-keygen -t ed25519 -C "your.email@example.com"
   ```

2. **Add to GitHub:**
   - Copy the public key from `C:\Users\akash\.ssh\id_ed25519.pub`
   - Go to GitHub Settings → SSH and GPG keys → New SSH key
   - Paste the key

3. **Update Remote (Optional):**
   ```powershell
   git remote set-url origin git@github.com:YOUR_USERNAME/SauceDemo_Automation_Cucumber.git
   ```

4. **Push:**
   ```powershell
   git push -u origin main
   ```

---

## Complete Command Sequence (Quick Reference)

Execute these commands in order:

```powershell
# Navigate to project
cd C:\Users\akash\eclipse-workspace\SauceDemo_Automation_Cucmber

# Initialize Git
git init

# Configure user (if first time)
git config --global user.name "Your Name"
git config --global user.email "your.email@example.com"

# Stage all files
git add .

# Create initial commit
git commit -m "Initial commit: SauceDemo Automation Framework"

# Add remote repository
git remote add origin https://github.com/YOUR_USERNAME/SauceDemo_Automation_Cucumber.git

# Rename branch to main (if needed)
git branch -M main

# Push to GitHub
git push -u origin main
```

---

## Troubleshooting

### Issue: "remote origin already exists"
```powershell
git remote remove origin
git remote add origin https://github.com/YOUR_USERNAME/SauceDemo_Automation_Cucumber.git
```

### Issue: Authentication failed
- Verify your Personal Access Token is correct
- Ensure the token has `repo` scope
- Try clearing cached credentials and re-entering

### Issue: "fatal: refusing to merge unrelated histories"
```powershell
git pull origin main --allow-unrelated-histories
```

### Issue: Large files being rejected
The `.gitignore` is already configured to exclude:
- `target/` directory
- IDE files (`.idea/`, `.iml`, etc.)
- Build artifacts

No additional action needed.

---

## What Gets Uploaded

**Included in Repository:**
- ✅ `src/` - All source code and test files
- ✅ `pom.xml` - Maven configuration
- ✅ `DOCUMENTATION/` - All documentation files
- ✅ `.gitignore` - Git ignore rules
- ✅ `README.md` - Project documentation
- ✅ `.mvn/` - Maven wrapper

**Excluded from Repository:**
- ❌ `target/` - Build artifacts
- ❌ `.idea/` - IDE metadata
- ❌ `logs/` - Runtime logs
- ❌ `.class` files - Compiled classes

---

## After Upload - Recommended Actions

1. **Add .gitignore for logs:**
   ```
   logs/
   ```
   (Already included in your `.gitignore`)

2. **Create additional branches:**
   ```powershell
   git checkout -b develop
   git push -u origin develop
   ```

3. **Add GitHub Topics** (Optional):
   - Go to Repository Settings → General → Topics
   - Add: `automation`, `cucumber`, `bdd`, `selenium`, `testing`

4. **Set up GitHub Actions** (Optional):
   - Create `.github/workflows/maven.yml` for CI/CD

---

## Future Commits

After the initial upload, for future changes:

```powershell
cd C:\Users\akash\eclipse-workspace\SauceDemo_Automation_Cucmber

# Make your changes...

# Stage changes
git add .

# Commit
git commit -m "Description of changes"

# Push
git push origin main
```

---

## Useful Git Commands

```powershell
# Check status
git status

# View commit history
git log --oneline

# View remote
git remote -v

# Create new branch
git checkout -b feature/branch-name

# Switch branch
git checkout main

# Update local from remote
git pull origin main

# Undo last commit (uncommitted changes)
git reset HEAD~1
```

---

## Need Help?

- GitHub Docs: https://docs.github.com
- Git Documentation: https://git-scm.com/doc
- Troubleshooting: https://docs.github.com/en/get-started

---

**Created:** April 4, 2026  
**Project:** SauceDemo Automation Framework with Cucumber

