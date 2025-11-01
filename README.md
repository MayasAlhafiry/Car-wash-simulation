# 🚗 Car Wash Simulation
## 📖 Overview

This project simulates a busy Car Wash and Gas Station using the Producer–Consumer problem pattern.
The simulation demonstrates synchronization and resource sharing using Semaphores, Mutex, and Threads in Java.

Each Car acts as a Producer, and each Pump (Service Bay) acts as a Consumer.
The system ensures no race conditions occur when multiple cars and pumps operate simultaneously.

## ⚙️ How it works
- There is a waiting area with limited space (a queue).
- A fixed number of pumps (service bays) work at the same time.
- If the waiting area is full, new cars must wait.
- When a pump is free, it takes the next car in the queue and starts service.
- When the service ends, the pump is released for the next car.

## 🧩 Classes
### 1. ServiceStation (Main Class)
- Creates and starts the Car (producers) and Pump (consumers) threads.
- Manages shared resources like:
       - Waiting queue
       - Semaphores (empty, full, mutex)
### 2. Semaphore
- Custom semaphore implementation (without using Java’s built-in classes).
- Provides:
       - P() → Wait / acquire
       - V() → Signal / release
### 3. Car (Producer)
- Represents a car arriving at the station.
- Waits if the queue is full.
- Enters the queue when space becomes available.
### 4. Pump (Consumer)
- Represents a pump that takes cars from the queue.
- Starts and finishes service for each car.
- Frees the pump for the next waiting car.
