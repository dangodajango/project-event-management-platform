# Functional Requirements: Event Management Platform

## User Roles

### As a user, I want to:
- Sign up and log in to the platform to access my account.
- Edit my profile details, including my name, email, and profile picture.
- Set preferences for receiving notifications.

### As an admin, I want to:
- View and manage all users and events.
- Deactivate users or events if they violate the platform's policies.

### As an event organizer, I want to:
- Create events with details such as title, description, location, date, and participant limit.
- Edit or delete my events.
- View the list of participants registered for my events.
- Be notified when a participant registers, cancels, or is promoted from the waitlist.
- Set whether my event is free or requires payment.

### As a participant, I want to:
- Browse and search for events by title, organizer, or location.
- Filter events based on date, category, or cost (free/paid).
- Register for events and receive confirmation of my registration.
- Cancel my registration if I can no longer attend.
- Join a waitlist if an event is full and be notified if a spot becomes available.
- Leave feedback or reviews for events I’ve attended.

---

## Event Management

### As an event organizer, I want to:
- Set a limit on the number of participants for my event.
- Automatically have a waitlist created for events that exceed the participant limit.

### As a participant, I want to:
- See if an event is free or paid before registering.
- Receive reminders before the event starts so I don’t forget.
- Have an easy way to view my upcoming events.

---

## Payment (For Paid Events)

### As an event organizer, I want to:
- Receive payments for ticketed events.
- Generate and provide receipts or invoices to participants for paid events.

### As a participant, I want to:
- Pay for events securely and receive a receipt for my payment.

---

## Notifications

### As a user, I want to:
- Be notified when an important update happens (e.g., event reminder, waitlist promotion, registration confirmation).
- Have control over what types of notifications I receive.

---

## Event Discovery

### As a participant, I want to:
- Search for events by keywords such as title or organizer name.
- Use filters to find events based on specific criteria (e.g., date, category, or cost).

---

## Feedback

### As a participant, I want to:
- Leave a review and rating for events I’ve attended.
- View feedback and ratings left by others for an event before deciding to register.

### As an event organizer, I want to:
- View feedback and ratings for my events to improve future events.

---

## Administrative Control

### As an admin, I want to:
- Monitor all activities on the platform, including user registrations and event creations.
- Take corrective actions like suspending users or removing events that don’t comply with policies.

---

## Extra Functional Goals

### As a user, I want to:
- Log in using my social media accounts for convenience.
- View real-time updates, such as the number of spots left in an event.
