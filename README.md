# Car Service Center Management System

A Java-based console application for managing a car service center. The system supports customer registration, appointment scheduling, service fee calculation with dynamic discounts, mechanic service reporting, and technician diagnostic reporting with automated text-file generation.

---

## Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Project Structure](#project-structure)
- [Class Diagram & Architecture](#class-diagram--architecture)
- [Customer Hierarchy](#customer-hierarchy)
- [Staff Roles](#staff-roles)
- [Appointment Scheduling Rules](#appointment-scheduling-rules)
- [Service Fee Calculation](#service-fee-calculation)
- [Getting Started](#getting-started)
- [Usage Guide](#usage-guide)
- [Testing](#testing)
- [Limitations & Notes](#limitations--notes)

---

## Overview

This application simulates a real-world car service center workflow where:
- **Front Desk / Receptionist** registers customers and schedules same-day service appointments.
- **Mechanics** view scheduled appointments and record repairs performed and parts used.
- **Technicians** add diagnostic results and generate finalized daily reports as text files.

The system enforces business rules such as 1-hour appointment slots, working hours (9 AM – 5 PM), duplicate customer prevention, and tiered service fee discounts.

---

## Features

| Feature | Description |
|---------|-------------|
| **Customer Registration** | Register private owners, staff members, and fleet clients with full details |
| **Duplicate Prevention** | National ID must be unique across all customers |
| **Appointment Scheduling** | 1-hour fixed slots between 9:00–17:00 with collision detection |
| **Polymorphic Billing** | Service fees calculated dynamically based on customer type |
| **Role-Based Access** | Separate menus for Receptionist, Mechanic, and Technician |
| **Service Reports** | Mechanics log repairs and parts per appointment |
| **Diagnostic Reports** | Technicians add diagnostics and export finalized reports to `.txt` |
| **JUnit Testing** | Unit tests for registration, scheduling, and fee calculation |

---

## Project Structure

```
Final/
├── Main.java                           # Entry point & CLI menus
│
├── Customer.java                       # Base customer entity
├── PrivateCarOwner.java                # Standard customer (no discount)
├── CenterStaffMembers.java             # Employee discount (50%)
├── FleetCompanyClients.java            # Fleet discount (30%)
│
├── Staff.java                          # Base staff with shared static data
├── FrontDesk.java                      # Receptionist: register & billing
├── FrontDesk_ScheduleAppointment.java  # Receptionist: scheduling logic
├── Mechanic.java                       # Mechanic: service reports
├── Technician.java                     # Technician: diagnostics & file export
│
├── Appointment.java                    # Appointment entity
│
├── RegisterCustomerTest.java           # JUnit: customer registration
├── ScheduleAppointmentTest.java        # JUnit: appointment validation
└── CalculationTest.java                # JUnit: fee calculation
```

---

## Class Diagram & Architecture

```
                    ┌─────────────────┐
                    │    <<abstract>> │
                    │    Customer     │
                    │  (base entity)  │
                    └────────┬────────┘
                             │
         ┌───────────────────┼───────────────────┐
         │                   │                   │
┌────────▼────────┐ ┌────────▼────────┐ ┌────────▼────────┐
│ PrivateCarOwner │ │CenterStaffMember│ │FleetCompanyClient│
│   (fee × 1.0)   │ │   (fee × 0.5)   │ │   (fee × 0.7)   │
└─────────────────┘ └─────────────────┘ └─────────────────┘

                    ┌─────────────────┐
                    │    <<abstract>> │
                    │      Staff      │
                    │ (shared lists)  │
                    └────────┬────────┘
                             │
         ┌───────────────────┼───────────────────┐
         │                   │                   │
┌────────▼────────┐ ┌────────▼────────┐ ┌────────▼────────┐
│    FrontDesk    │ │FrontDesk_Schedule│ │    Mechanic     │
│  (register/bill)│ │   (scheduling)   │ │ (service report)│
└─────────────────┘ └─────────────────┘ └─────────────────┘
                             │
                    ┌────────▼────────┐
                    │   Technician    │
                    │ (diagnostics /  │
                    │  file export)   │
                    └─────────────────┘
```

---

## Customer Hierarchy

All customers share the same data model but receive different service fee discounts via polymorphism.

| Customer Type | Discount | Fee (Base = 50) |
|---------------|----------|-----------------|
| `PrivateCarOwner` | 0% | 50.0 |
| `CenterStaffMembers` | 50% | 25.0 |
| `FleetCompanyClients` | 30% | 35.0 |

### Customer Fields
- `fullName`
- `address`
- `nationalID` *(unique identifier)*
- `contactNumber`
- `carType`
- `carModel`
- `carPlate`

---

## Staff Roles

### 1. Front Desk (Receptionist)
**Menu Options:**
- **A** — Register new customers and their cars
- **B** — Schedule a same-day service appointment
- **C** — Calculate and print service bill by National ID
- **D** — Print all daily appointments with customer and car details

### 2. Mechanic
**Menu Options:**
- **A** — View list of all scheduled service appointments
- **B** — Add service report (repairs performed, parts used)

### 3. Technician
**Menu Options:**
- **A** — Add diagnostic results (issues found, recommended actions)
- **B** — Generate a daily report in text format for each serviced car

> Reports are only generated for appointments that have both **repairs performed** and **diagnostic report** completed.

---

## Appointment Scheduling Rules

The `FrontDesk_ScheduleAppointment` class enforces the following constraints:

1. **Duration** — Appointments must be exactly 1 hour (`end == start + 1 hour`).
2. **Working Hours** — Start time must be between 09:00 and 17:00 (inclusive), on the hour (`:00`).
3. **No Double Booking** — Only one appointment per time slot per day.
4. **Customer Required** — A customer must exist in the system before scheduling.

---

## Service Fee Calculation

Fees are computed polymorphically:

```java
// Base fee defined in Customer.java
protected double serviceFee = 50;

// CenterStaffMembers.java
@Override
public double getServiceFee() {
    return serviceFee * 0.5;  // 25.0
}

// FleetCompanyClients.java
@Override
public double getServiceFee() {
    return serviceFee * 0.7;  // 35.0
}

// PrivateCarOwner.java (inherits base)
public double getServiceFee() {
    return serviceFee;        // 50.0
}
```

---

## Getting Started

### Prerequisites
- Java JDK 17 or higher
- JUnit 5 (for running tests)

### Compilation
```bash
# Compile all source files
javac Final/*.java

# Run the application
java Final.Main
```

### Running Tests
```bash
# With JUnit on classpath
java -cp .:junit-platform-console-standalone.jar org.junit.platform.console.ConsoleLauncher --select-package Final
```

---

## Usage Guide

### Main Menu
```
Choose your choice:
A-Receptionist
B-Mechanic
C-Technician
D-exit
```

### Registering a Customer (Receptionist → A)
1. Enter personal details (name, address, national ID, contact, car info).
2. Choose customer type:
   - `1` — Private car owner
   - `2` — Center staff member
   - `3` — Fleet company client

### Scheduling an Appointment (Receptionist → B)
1. Select a customer from the registered list.
2. Enter day, month, start hour, and end hour.
3. The system validates the slot and books it.

### Calculating a Bill (Receptionist → C)
1. Enter the customer's National ID.
2. The system prints the discounted service fee.

### Adding a Service Report (Mechanic → B)
1. View the appointment list.
2. Select an appointment by number.
3. Enter repairs performed and parts used.

### Generating Reports (Technician → B)
1. Ensure appointments have both service and diagnostic data.
2. The system exports one `.txt` file per completed appointment:
   ```
   appointment_<nationalID>_<startTime>.txt
   ```

---

## Testing

The project includes three JUnit 5 test classes:

### `RegisterCustomerTest.java`
| Test | Purpose |
|------|---------|
| `testAddCustomerToEmptyList` | Verifies customer is added to empty list |
| `testNullCustomerNotAdded` | Ensures null input is rejected |
| `testDuplicateCustomerNotAdded` | Prevents duplicate National IDs |

### `ScheduleAppointmentTest.java`
| Test | Purpose |
|------|---------|
| `testValidTimeSlot` | Accepts a standard 1-hour slot within working hours |
| `testInvalidTimeSlot_NotFullHour` | Rejects slots != 1 hour |
| `testInvalidTimeSlot_OutOfWorkingHours` | Rejects slots before 9 AM or after 5 PM |
| `testSlotAlreadyBooked` | Prevents double-booking the same time slot |

### `CalculationTest.java`
| Test | Purpose |
|------|---------|
| `testPrivateCarOwnerFee` | Asserts fee = 50.0 |
| `testCenterStaffMemberFee` | Asserts fee = 25.0 (50% off) |
| `testFleetCompanyClientFee` | Asserts fee = 35.0 (30% off) |

---

## Limitations & Notes

1. **In-Memory Storage** — All data is stored in static `ArrayList` objects. Data is lost when the program exits.
2. **No Persistent Database** — No JDBC, file-based storage, or serialization is implemented (except technician text reports).
3. **Same-Day Only** — Appointment scheduling does not support multi-day booking or recurring appointments.
4. **Static Shared State** — `customerList` and `appointmentList` are `static` in `Staff`, meaning all staff instances share the same data. This is by design for simplicity but limits scalability.
5. **No Edit/Delete** — Appointments and customers cannot be modified or removed after creation.
6. **Hardcoded Year** — Appointment scheduling uses a fixed year (`2025`) in `Main.java`.
7. **No Login / Authentication** — Anyone can access any staff role menu without credentials.

---

## Future Enhancements

- [ ] Persist customers and appointments to a database or serialized files
- [ ] Add edit/delete functionality for appointments and customers
- [ ] Implement login credentials per staff role
- [ ] Support multi-day and recurring appointment scheduling
- [ ] Add email/SMS notification simulation for appointment reminders
- [ ] Graphical User Interface (JavaFX or Swing)
- [ ] Inventory tracking for parts used

---

## License

This project is provided for educational purposes in object-oriented programming and software engineering coursework.

---

*Built with Java, inheritance, and a lot of `System.out.println`.*
