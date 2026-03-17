# 📍 PinPoint - Xaero's World Map Addon

[![Fabric](https://img.shields.io/badge/Mod%20Loader-Fabric-dbd0b4.svg?style=flat-square)](https://fabricmc.net)
[![License](https://img.shields.io/badge/License-GPLv3-red.svg?style=flat-square)](LICENSE)

**PinPoint** is a lightweight client-side Fabric mod that adds a convenient `/setwaypoint` command for creating waypoints in [Xaero's World Map](https://www.curseforge.com/minecraft/mc-mods/xaeros-world-map) directly from in-game chat. It automatically detects coordinates in chat messages and suggests them as you type!

## ✨ Features

### 🎯 `/setwaypoint <x> <y> <z>`
- Instantly creates a red waypoint in Xaero's World Map
- Automatically rotates your camera to face the new waypoint
- Waypoints are named "Waypoint" with "W" symbol (customizable via lang files)

### 💬 Smart Chat Detection
- Automatically scans all chat messages for coordinates
- Recognizes coordinates in various formats: `449, 167, 2116`, `1000 64 -200`, `-1234,56,789`

### ⌨️ Intelligent Tab Completion
- When typing `/setwaypoint`, tab completion shows recently mentioned coordinates from chat
- Each suggestion displays the full coordinate set for easy reference
- Helps you quickly create waypoints from coordinates shared by other players

## 🎮 Usage

### Creating a waypoint
```
/setwaypoint 100 64 200
```
This creates a waypoint at X=100, Y=64, Z=200.

### Using chat coordinates
1. Someone in chat says: "I found diamonds at -123, 45, 789!"
2. Type `/setwaypoint` and press Tab
3. The coordinates `-123, 45, 789` will appear as a suggestion
4. Select it and press Enter
5. Your camera automatically turns to face the waypoint!

## 🤝 Contributing

Found a bug? Have a suggestion? Feel free to:
- Open an [Issue](https://github.com/Digital-Twilight/PinPoint/issues)
- Submit a [Pull Request](https://github.com/Digital-Twilight/PinPoint/pulls)

## 📜 License

This project is licensed under the GNU General Public License v3.0 - see the [LICENSE](LICENSE) file for details.

## 🙏 Acknowledgements

- [xaero96](https://twitter.com/xaero96) for the amazing Xaero's World Map and Minimap mods
- The Fabric team for the awesome modding toolchain
- All users who provide feedback and suggestions!

---

**Note:** This mod is not officially affiliated with Xaero's World Map or its author. It's a third-party addon created by a fan.
