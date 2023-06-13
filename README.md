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

- Stability: Describe the concept of coin stability in Othello and how it is used as a heuristic. Explain the weights assigned to different categories of stability.
- Mobility: Explain the strategy of restricting the opponent's mobility and increasing one's own mobility. Differentiate between actual and potential mobility and describe how their heuristic values are calculated.
- Evaporation: Describe the evaporation strategy in the reverse game and its aim to reduce the opponent's available moves or liberties. Explain how having a lower count of discs can limit the opponent's liberties.

## Supported Game Modes

- Human vs Human: Explain how two human players can play against each other.
- Human vs AI Agent: Describe the mode where a human player can play against the AI agent.
- AI Agent vs AI Agent: Explain how the AI agents can play against each other.

## Difficulty Levels

- Maximum Difficulty Level: Describe the highest difficulty level supported, the depth used in the search algorithm, and its impact on gameplay.
- Easy Difficulty Level: Explain the lower difficulty level supported and the corresponding depth used.

