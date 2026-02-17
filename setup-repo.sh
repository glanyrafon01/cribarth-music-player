#!/bin/bash

# Configuration
REPO_URL="https://glanyrafon01.github.io/cribarth-music-player"
LIST_FILE="/etc/apt/sources.list.d/cribarth-music-player.list"

echo "Adding Cribarth Music Player repository..."

# Add repository to sources.list.d
echo "deb [trusted=yes] $REPO_URL stable main" | sudo tee $LIST_FILE

echo "Repository added. Updating package list..."
sudo apt update

echo "You can now install the player with: sudo apt install cribarth-music-player"
