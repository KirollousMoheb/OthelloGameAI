# OthelloGameAI

# Project Name

This repository contains the source code and resources for the Project Name. It is a brief description of the project.

## Table of Contents
1. [Introduction](#introduction)
2. [Demo Video](#demo-video)
3. [Installation](#installation)
4. [Usage](#usage)
5. [Features](#features)
6. [Supported Algorithms](#supported-algorithms)
7. [Heuristics](#heuristics)
8. [Supported Game Modes](#supported-game-modes)
9. [Difficulty Levels](#difficulty-levels)

## Introduction

Project is a game-playing application that utilizes various algorithms and heuristics to provide an engaging gameplay experience. This project showcases the implementation of Minmax search and Alpha-beta pruning algorithms, along with multiple heuristics such as stability, mobility, and evaporation.

## Demo Video

[![YouTube](https://img.shields.io/badge/YouTube-FF0000?style=for-the-badge&logo=youtube&logoColor=white)](https://www.youtube.com/watch?v=h8EKg_wFf7Y)

A demo video showcasing the project can be found on YouTube. Click on the badge above to watch the video.

## Installation

1. Clone the repository or download the source code.
2. Ensure you have Java Runtime Environment installed on your Windows OS.
3. Download the executable JAR file from the following [![Google Drive](https://img.shields.io/badge/Google%20Drive-4285F4?style=for-the-badge&logo=googledrive&logoColor=white)](https://drive.google.com/drive/folders/1WaQ7fgfHR8xO502i0cxkvTNXl2FdnPQG) link.
4. Place the JAR file in a convenient location on your system.

## Usage

To start playing Project Name, execute the downloaded JAR file by double-clicking it or using the command `java -jar projectname.jar` in the terminal/console.

## Features

Project  includes the following features:
- Display of valid moves for the current player
- Restriction of playing invalid moves

## Supported Algorithms

Project utilizes the following game-playing algorithms:

1. Minmax Search:
   - The minimax search algorithm is used to explore the game tree depth-first.
   - It assigns utility values to the leaves using heuristic functions.
   - Utility values are propagated back to the root based on whether the node is a min or max node.
   - The max player aims to maximize their utility value, while the min player aims to minimize it.

2. Alpha-beta Pruning:
   - The alpha-beta search algorithm is similar to minimax but performs efficient pruning.
   - It determines when a branch is no longer useful and prunes it.
   - This allows the algorithm to explore the game tree to greater depths and provide a more powerful lookahead.

## Heuristics

Project incorporates the following heuristics for game playing:

1. Stability
   - The concept of coin stability in Othello is used as a heuristic.
   - Stability is a measure of a coin's vulnerability to being flanked, and coins are classified as stable, semi-stable, or unstable.
   - Corners are always considered stable, and building upon them increases the stability of nearby coins.
   - Weights are assigned to each category, and a final stability value for the player is calculated by summing up the weights. A typical weight scheme could be 1 for stable coins, -1 for unstable coins, and 0 for semi-stable coins.

2. Mobility
   - Mobility is another strategy employed in the game to restrict the opponent's mobility and increase your own.
   - It can be divided into actual mobility and potential mobility.
   - Actual mobility represents the number of legal moves a player has, while potential mobility takes into account moves that are currently not legal but could become legal in the future.
   - The heuristic values for actual and potential mobility are calculated using different methods, considering both complexity and effectiveness.

3. Evaporation
   - The evaporation strategy is employed in the reverse game to reduce the opponent's available moves or liberties.
   - By having a lower count of discs, the opponent's liberties are limited, forcing them to make poor moves.
   - Unlike the regular game, being wiped out is also counted as a victory, but caution is still needed to prevent the opponent from evaporating completely.

## Supported Game Modes

Project  offers the following game modes:

1. Human vs Human: Play against another human player on the same device.
2. Human vs AI Agent: Challenge yourself by playing against an AI agent.
3. AI Agent vs AI Agent: Sit back and watch as two AI agents battle it out.

## Difficulty Levels

Project provides the following difficulty levels:

- Maximum Difficulty (Depth 9): At the highest difficulty level, the AI employs advanced strategies and algorithms to provide a challenging gameplay experience. Be prepared for a tough match!
- Easy (Depth 2): If you're looking for a more relaxed gameplay experience, you can choose the easy difficulty level. The AI's moves are less complex, making it more accessible for beginners.

Please note that the AI at the maximum difficulty level is highly skilled, and it becomes very hard to win against it. The AI will consistently outperform human players.
