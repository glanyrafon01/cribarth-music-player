#!/bin/bash

# Source icon (replace with your actual filename if different)
SRC_ICON="build/icon.png"

# Output directory for resized icons
OUT_DIR="build/icons"

# Ensure output directory exists
mkdir -p "$OUT_DIR"

# Define standard Linux icon sizes
SIZES=(16 24 32 48 64 128 256 512)

echo "Generating resized icons from $SRC_ICON..."

for size in "${SIZES[@]}"; do
    convert "$SRC_ICON" -resize "${size}x${size}" "$OUT_DIR/icon-${size}x${size}.png"
done

echo "Icons generated in $OUT_DIR:"
ls "$OUT_DIR"
