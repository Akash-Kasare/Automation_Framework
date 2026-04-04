# GitHub Repository Creation - Visual Guide

## Part 1: Create Repository on GitHub Website

### Step 1: Go to GitHub.com
- Open browser → https://github.com
- Sign in to your account (top-right corner)

### Step 2: Create New Repository
```
Click: + Icon (top-right) → "New repository"
```

### Step 3: Repository Settings

**Fill in these details:**

| Field | Value | Example |
|-------|-------|---------|
| **Repository name** | Your project name | `SauceDemo_Automation_Cucumber` |
| **Description** | Brief description | `Selenium WebDriver automation testing framework using Cucumber BDD` |
| **Visibility** | Public or Private | Public (if you want to share) |
| **README** | ❌ Leave UNCHECKED | We have our own |
| **.gitignore** | ❌ Leave UNCHECKED | We have our own |
| **License** | Optional | Choose if desired |

### Step 4: Click "Create repository"

You'll see a page like this:

```
Quick setup — if you've done this kind of thing before
or
HTTPS: https://github.com/YOUR_USERNAME/SauceDemo_Automation_Cucumber.git
SSH: git@github.com:YOUR_USERNAME/SauceDemo_Automation_Cucumber.git
```

**Save this URL** - You'll need it in Step 5 below!

---

## Part 2: Upload from Your Computer

### Step 1: Open PowerShell
1. Press `Windows + X`
2. Select "Windows PowerShell" or "Terminal"

### Step 2: Navigate to Your Project
```powershell
cd C:\Users\akash\eclipse-workspace\SauceDemo_Automation_Cucmber
```

### Step 3: Check Git Installation
```powershell
git --version
```
Should return: `git version 2.xx.x.windows.1`

### Step 4: Configure Git User (First Time Only)
```powershell
git config --global user.name "Akash"
git config --global user.email "your.email@example.com"
```

Verify:
```powershell
git config --global --list
```

### Step 5: Initialize Repository
```powershell
git init
```

Output: `Initialized empty Git repository in C:\Users\akash\eclipse-workspace\SauceDemo_Automation_Cucmber\.git/`

### Step 6: Stage All Files
```powershell
git add .
```

Check what's being added:
```powershell
git status
```

Example output:
```
On branch master

No commits yet

Changes to be committed:
  new file:   pom.xml
  new file:   .gitignore
  new file:   GITHUB_SETUP_GUIDE.md
  new file:   upload-to-github.ps1
  (and many more files...)

Untracked files:
  (none)
```

### Step 7: Create Initial Commit
```powershell
git commit -m "Initial commit: SauceDemo Automation Framework"
```

Output should show files being committed.

### Step 8: Add Your GitHub Repository as Remote
```powershell
git remote add origin https://github.com/YOUR_USERNAME/SauceDemo_Automation_Cucumber.git
```

Replace `YOUR_USERNAME` with your actual GitHub username!

Verify:
```powershell
git remote -v
```

Output:
```
origin  https://github.com/YOUR_USERNAME/SauceDemo_Automation_Cucumber.git (fetch)
origin  https://github.com/YOUR_USERNAME/SauceDemo_Automation_Cucumber.git (push)
```

### Step 9: Rename Branch to "main"
```powershell
git branch -M main
```

### Step 10: Push to GitHub
```powershell
git push -u origin main
```

**First-time authentication:**
- If using HTTPS: Enter your GitHub username and Personal Access Token
- A browser window might open for authentication

---

## Getting Authentication to Work

### Option A: Personal Access Token (Recommended for HTTPS)

1. **Generate Token on GitHub:**
   - Go to Settings (top-right profile) → Developer settings → Personal access tokens
   - Click "Tokens (classic)"
   - Click "Generate new token (classic)"
   - Select scope: `repo` (Full control of private repositories)
   - Click "Generate token"
   - **Copy the token** (you won't see it again!)

2. **Use Token in Git:**
   - When Git asks for password: Paste the token
   - Save it in Windows Credential Manager for future use

3. **Save Credentials (Optional):**
   ```powershell
   git config --global credential.helper wincred
   ```

### Option B: SSH Key (Advanced)

1. **Generate SSH Key:**
   ```powershell
   ssh-keygen -t ed25519 -C "your.email@example.com"
   ```
   - Press Enter for default location
   - Press Enter for no passphrase (or create one)

2. **Add to GitHub:**
   - Copy key from: `C:\Users\akash\.ssh\id_ed25519.pub`
   - Go to GitHub Settings → SSH and GPG keys
   - Click "New SSH key"
   - Paste the key

3. **Update Remote URL:**
   ```powershell
   git remote set-url origin git@github.com:YOUR_USERNAME/SauceDemo_Automation_Cucumber.git
   ```

4. **Push:**
   ```powershell
   git push -u origin main
   ```

---

## Visual Workflow Diagram

```
┌─────────────────────────────────┐
│   Create Repo on GitHub.com     │
│  (Get HTTPS/SSH URL)            │
└────────────┬────────────────────┘
             │
             ▼
┌─────────────────────────────────┐
│  cd to project folder           │
│  git init                       │
└────────────┬────────────────────┘
             │
             ▼
┌─────────────────────────────────┐
│  git add .                      │
│  git commit -m "message"        │
└────────────┬────────────────────┘
             │
             ▼
┌─────────────────────────────────┐
│  git remote add origin [URL]    │
│  git branch -M main             │
└────────────┬────────────────────┘
             │
             ▼
┌─────────────────────────────────┐
│  git push -u origin main        │
│  (Authenticate with PAT/SSH)    │
└────────────┬────────────────────┘
             │
             ▼
┌─────────────────────────────────┐
│  ✓ Repository Created!          │
│  View at GitHub.com             │
└─────────────────────────────────┘
```

---

## Verify Upload Success

After `git push`, verify your upload:

1. **Go to Your Repository:**
   - Visit: `https://github.com/YOUR_USERNAME/SauceDemo_Automation_Cucumber`

2. **Check Main Files Are Present:**
   - ✅ `README.md`
   - ✅ `pom.xml`
   - ✅ `src/` folder
   - ✅ `DOCUMENTATION/` folder
   - ✅ `.gitignore`

3. **Check Files Are Excluded (Good!):**
   - ✅ No `target/` folder
   - ✅ No `.idea/` folder
   - ✅ No `logs/` folder

4. **Check Commit:**
   - Click "X commits" to verify your initial commit is there

---

## After Upload: Managing Your Repository

### Make Future Changes

```powershell
# Make edits to your files...

# Stage changes
git add .

# Commit
git commit -m "Added new test scenarios"

# Push
git push origin main
```

### View Changes Online
- Refresh GitHub.com
- You'll see updated files
- Check commit history: Click "Commits"

### Common Tasks

**Create new branch:**
```powershell
git checkout -b feature/new-tests
git push -u origin feature/new-tests
```

**View history:**
```powershell
git log --oneline
```

**Undo last commit (before push):**
```powershell
git reset HEAD~1
```

---

## Repository URL Reference

Your repository will be accessible at:
```
https://github.com/YOUR_USERNAME/SauceDemo_Automation_Cucumber
```

Share this URL with team members who need access!

---

## Help & Support

| Topic | Resource |
|-------|----------|
| Git Commands | `git help <command>` |
| GitHub Docs | https://docs.github.com |
| Git Tutorials | https://git-scm.com/doc |
| SSH Issues | https://docs.github.com/en/authentication/connecting-to-github-with-ssh |

---

**Remember:** First upload is the trickiest! After this, future uploads will be easy.

---

**Created:** April 4, 2026  
**Last Updated:** April 4, 2026

