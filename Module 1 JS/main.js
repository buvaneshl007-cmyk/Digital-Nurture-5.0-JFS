// ============================================
// LOCAL COMMUNITY EVENT PORTAL - MAIN.JS
// Comprehensive JavaScript Exercise Project
// ============================================

// ===================================================
// MODULE 1: JAVASCRIPT BASICS & SETUP
// ===================================================
console.log("Welcome to the Community Portal");

// Show alert when page is fully loaded
window.addEventListener("load", function () {
    console.log("Page fully loaded at", new Date().toLocaleTimeString());
    alert("🎉 Welcome to Your Community Event Portal!\n\nReady to discover amazing events and connect with your neighbors? Let's go! 🚀");
});

// ===================================================
// MODULE 2: SYNTAX, DATA TYPES, AND OPERATORS
// ===================================================

// Event Constructor - demonstrates object creation and data types
class Event {
    constructor(id, name, date, category, location, description, icon, availableSeats) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.category = category;
        this.location = location;
        this.description = description;
        this.icon = icon;
        this.availableSeats = availableSeats;
        this.registeredUsers = 0;
    }

    // Module 3: Method with try-catch error handling
    registerUser(numberOfTickets = 1) {
        try {
            if (typeof numberOfTickets !== "number" || numberOfTickets < 1) {
                throw new Error("Invalid ticket quantity");
            }

            if (this.availableSeats < numberOfTickets) {
                throw new Error("Not enough seats available");
            }

            // Use -- operator to decrement available seats (Module 2)
            for (let i = 0; i < numberOfTickets; i++) {
                this.availableSeats--;
                this.registeredUsers++;
            }
            return { success: true, message: `Successfully registered ${numberOfTickets} ticket(s)` };
        } catch (error) {
            console.error("Registration Error:", error.message);
            return { success: false, message: error.message };
        }
    }

    // Module 5: Method to check availability using prototype pattern
    checkAvailability() {
        return this.availableSeats > 0;
    }

    // Module 10: Template literal for formatted event info
    getEventInfo() {
        return `📌 ${this.name} - ${this.date} | ${this.location} | ${this.availableSeats} seats left`;
    }
}

// ===================================================
// MODULE 3, 4: SAMPLE EVENT DATA WITH CONST & LET
// ===================================================

// Using const for event list (won't change)
const eventsList = [
    new Event(1, "Summer Jazz Concert", "2026-06-15", "music", "downtown", 
              "Enjoy live jazz music in the heart of downtown.", "🎵", 150),
    new Event(2, "Baking Workshop", "2026-06-20", "workshop", "uptown", 
              "Learn professional baking techniques from expert pastry chefs.", "📚", 50),
    new Event(3, "Community Soccer Game", "2026-06-22", "sports", "suburb", 
              "Join the community soccer tournament this summer!", "⚽", 100),
    new Event(4, "Networking Social", "2026-06-25", "social", "waterfront", 
              "Meet like-minded professionals in a casual setting.", "👥", 200),
    new Event(5, "Food Festival", "2026-07-01", "food", "downtown", 
              "Experience cuisines from around the world.", "🍽️", 300),
    new Event(6, "Yoga & Meditation", "2026-07-05", "workshop", "waterfront", 
              "Find your inner peace with guided yoga sessions.", "🧘", 80),
    new Event(7, "Movie Night Outdoors", "2026-07-10", "social", "suburb", 
              "Watch classic movies under the stars with the community.", "🎬", 250),
    new Event(8, "Running Marathon", "2026-07-15", "sports", "downtown", 
              "5K and 10K running event for all fitness levels.", "🏃", 500),
    new Event(9, "Photography Walk", "2026-07-20", "workshop", "waterfront", 
              "Learn photography basics while exploring scenic locations.", "📸", 60),
    new Event(10, "Garden Expo", "2026-08-01", "social", "uptown", 
               "Discover tips for growing your own garden.", "🌳", 180),
];

// Using let for registrations (will change)
let userRegistrations = [];

// ===================================================
// MODULE 4: FUNCTIONS, CLOSURES, HIGHER-ORDER FUNCTIONS
// ===================================================

// Closure function to track registrations by category
function createCategoryTracker() {
    const categoryRegistrations = {};

    return {
        addRegistration(category, count) {
            categoryRegistrations[category] = (categoryRegistrations[category] || 0) + count;
        },
        getTotal(category) {
            return categoryRegistrations[category] || 0;
        },
        getAllTotals() {
            return categoryRegistrations;
        },
    };
}

const registrationTracker = createCategoryTracker();

// Module 4: Function with callback parameter (higher-order function)
function filterEventsByCategory(callback, category = "") {
    if (category === "") {
        return eventsList;
    }
    return eventsList.filter(event => event.category === category);
}

// Module 4: Function with callback
function filterEventsByLocation(callback, location = "") {
    if (location === "") {
        return eventsList;
    }
    return callback(eventsList.filter(event => event.location === location));
}

// Module 4: Add event function
function addEvent(name, date, category, location, description, icon, seats) {
    const newEvent = new Event(
        eventsList.length + 1,
        name,
        date,
        category,
        location,
        description,
        icon,
        seats
    );
    eventsList.push(newEvent);
    console.log(`Event added: ${name}`);
    return newEvent;
}

// Module 4: Register user function
function registerUser(eventId, userName, userEmail, numberOfTickets) {
    try {
        const event = eventsList.find(e => e.id === eventId);
        if (!event) {
            throw new Error("Event not found");
        }

        if (!userName || !userEmail) {
            throw new Error("Name and email are required");
        }

        const result = event.registerUser(numberOfTickets);
        if (result.success) {
            const registration = {
                id: userRegistrations.length + 1,
                eventId,
                eventName: event.name,
                userName,
                userEmail,
                numberOfTickets,
                registeredAt: new Date(),
            };
            userRegistrations.push(registration);
            registrationTracker.addRegistration(event.category, 1);
            console.log(`User ${userName} registered for ${event.name}`);
            return registration;
        } else {
            throw new Error(result.message);
        }
    } catch (error) {
        console.error("Registration failed:", error.message);
        throw error;
    }
}

// ===================================================
// MODULE 5: OBJECTS AND PROTOTYPES
// ===================================================

// Add prototype method for Event class
Event.prototype.getEventDetails = function () {
    return {
        name: this.name,
        date: this.date,
        category: this.category,
        location: this.location,
        availableSeats: this.availableSeats,
        registered: this.registeredUsers,
    };
};

// Module 5: Using Object.entries() to list object keys and values
function displayEventDetails(event) {
    console.log(`\n📋 Event Details for: ${event.name}`);
    Object.entries(event.getEventDetails()).forEach(([key, value]) => {
        console.log(`   ${key}: ${value}`);
    });
}

// ===================================================
// MODULE 6: ARRAYS AND METHODS
// ===================================================

// Module 6: Filter music events using .filter()
function getMusicEvents() {
    return eventsList.filter(event => event.category === "music");
}

// Module 6: Map to format event display cards using .map()
function getFormattedEventCards() {
    return eventsList.map(event => ({
        title: `${event.icon} ${event.name}`,
        subtitle: `${event.category.toUpperCase()} • ${event.location}`,
        info: event.getEventInfo(),
    }));
}

// Module 6: Get available events using filter and custom condition
function getAvailableEvents() {
    return eventsList.filter(event => event.checkAvailability() && isEventUpcoming(event.date));
}

// Module 10: Spread operator to clone array before filtering
function getFilteredEventsCopy(category = "", location = "") {
    let eventsCopy = [...eventsList]; // Spread operator creates shallow copy

    if (category) {
        eventsCopy = eventsCopy.filter(e => e.category === category);
    }
    if (location) {
        eventsCopy = eventsCopy.filter(e => e.location === location);
    }
    return eventsCopy;
}

// ===================================================
// MODULE 7: DOM MANIPULATION
// ===================================================

function renderEvents(events = eventsList) {
    const container = document.getElementById("eventsContainer");
    container.innerHTML = ""; // Clear previous content

    if (events.length === 0) {
        container.innerHTML = '<div class="no-events-message">No events found.</div>';
        return;
    }

    // Module 7: Create event cards using createElement()
    events.forEach(event => {
        const eventCard = document.createElement("div");
        eventCard.className = "event-card";
        eventCard.id = `event-${event.id}`;

        const seatsClass = event.availableSeats === 0 ? "full" : "available";
        const seatsText = event.availableSeats === 0 
            ? "🚫 Event Full - Check back soon!" 
            : `✨ ${event.availableSeats} spots left!`;

        // Module 10: Template literals for string formatting
        eventCard.innerHTML = `
            <div class="event-card-header">
                <div class="event-card-icon">${event.icon}</div>
                <div class="event-card-title">${event.name}</div>
                <span class="event-card-category">${event.category}</span>
            </div>
            <div class="event-card-body">
                <div class="event-detail">
                    <span class="event-detail-icon">📅</span>
                    <span class="event-detail-text">${event.date}</span>
                </div>
                <div class="event-detail">
                    <span class="event-detail-icon">📍</span>
                    <span class="event-detail-text">${event.location}</span>
                </div>
                <div class="event-description">${event.description}</div>
                <div class="seats-available ${seatsClass}">${seatsText}</div>
                <div class="event-card-footer">
                    <button class="btn btn-primary" onclick="scrollToRegistration(${event.id})" 
                            ${event.availableSeats === 0 ? "disabled" : ""}>
                        ${event.availableSeats === 0 ? "Coming Soon" : "I'm Interested! 🙌"}
                    </button>
                    <button class="btn btn-secondary" onclick="showEventDetails(${event.id})">
                        Learn More
                    </button>
                </div>
            </div>
        `;

        container.appendChild(eventCard);
    });
}

// Module 7: Access DOM elements and update UI
function renderRegistrations() {
    const container = document.getElementById("registrationsContainer");
    container.innerHTML = "";

    if (userRegistrations.length === 0) {
        container.innerHTML = '<div class="no-events-message">👀 No bookings yet! Head to the events section above and find something awesome to join. You deserve some fun! 🎊</div>';
        return;
    }

    userRegistrations.forEach(registration => {
        const regCard = document.createElement("div");
        regCard.className = "registration-card";

        regCard.innerHTML = `
            <div class="registration-card-info">
                <h4>🎉 ${registration.eventName}</h4>
                <p><strong>Your Name:</strong> ${registration.userName}</p>
                <p><strong>Email:</strong> ${registration.userEmail}</p>
                <p><strong>Tickets:</strong> ${registration.numberOfTickets} ${registration.numberOfTickets === 1 ? "person" : "people"}</p>
                <p><strong>Registered:</strong> ${registration.registeredAt.toLocaleDateString()}</p>
            </div>
            <div class="registration-card-actions">
                <button class="btn btn-danger" onclick="cancelRegistration(${registration.id})">
                    😢 Cancel Booking
                </button>
            </div>
        `;

        container.appendChild(regCard);
    });
}

// Helper function to show event details
function showEventDetails(eventId) {
    const event = eventsList.find(e => e.id === eventId);
    if (event) {
        const availabilityText = event.availableSeats === 0 
            ? "This event is full, but check back soon!" 
            : `${event.availableSeats} spots still available!`;
        const message = `
🎉 ${event.name}

${event.description}

📅 When: ${event.date}
📍 Where: ${event.location}
🎫 Status: ${availabilityText}

Ready to join? Scroll down and register now! 👇`;
        alert(message);
    }
}

// Helper function to scroll to registration form
function scrollToRegistration(eventId) {
    const selectElement = document.getElementById("eventSelect");
    selectElement.value = eventId;
    document.getElementById("register").scrollIntoView({ behavior: "smooth" });
}

// ===================================================
// MODULE 8: EVENT HANDLING
// ===================================================

// Module 8: Setup all event listeners
function setupEventListeners() {
    // Module 8: onchange for category filter
    document.getElementById("categoryFilter").addEventListener("change", function () {
        applyFilters();
    });

    // Module 8: onchange for location filter
    document.getElementById("locationFilter").addEventListener("change", function () {
        applyFilters();
    });

    // Module 8: keydown for search (quick search by name)
    document.getElementById("searchInput").addEventListener("keydown", function (event) {
        if (event.key === "Enter" || event.key === "Backspace" || event.key.length === 1) {
            applyFilters();
        }
    });

    // Real-time search as user types
    document.getElementById("searchInput").addEventListener("keyup", function () {
        applyFilters();
    });

    // Reset filters button
    document.getElementById("resetFilters").addEventListener("click", function () {
        document.getElementById("categoryFilter").value = "";
        document.getElementById("locationFilter").value = "";
        document.getElementById("searchInput").value = "";
        renderEvents(eventsList);
    });

    // Module 8: onclick for registration form submit (using addEventListener instead of onclick)
    document.getElementById("registrationForm").addEventListener("submit", handleFormSubmit);

    // Debug toggle
    document.getElementById("toggleDebug").addEventListener("click", function () {
        const debugInfo = document.getElementById("debugInfo");
        debugInfo.style.display = debugInfo.style.display === "none" ? "block" : "none";
    });
}

// Apply all active filters
function applyFilters() {
    const category = document.getElementById("categoryFilter").value;
    const location = document.getElementById("locationFilter").value;
    const searchTerm = document.getElementById("searchInput").value.toLowerCase();

    let filtered = getFilteredEventsCopy(category, location);

    if (searchTerm) {
        filtered = filtered.filter(event =>
            event.name.toLowerCase().includes(searchTerm) ||
            event.description.toLowerCase().includes(searchTerm)
        );
    }

    renderEvents(filtered);
}

// ===================================================
// MODULE 11: WORKING WITH FORMS
// ===================================================

// Module 11: Form validation and submission handler
function handleFormSubmit(event) {
    // Module 11: Prevent default form behavior
    event.preventDefault();

    // Clear previous messages
    document.getElementById("successMessage").style.display = "none";
    document.getElementById("errorMessage").style.display = "none";

    // Module 11: Capture form inputs using form.elements
    const form = event.target;
    const userName = form.elements.userName.value.trim();
    const userEmail = form.elements.userEmail.value.trim();
    const eventId = parseInt(form.elements.eventSelect.value);
    const numberOfTickets = parseInt(form.elements.numberOfTickets.value);

    // Module 11: Validate inputs and show errors inline
    const errors = validateRegistrationForm(userName, userEmail, eventId, numberOfTickets);

    if (Object.keys(errors).length > 0) {
        displayFormErrors(errors);
        return;
    }

    // Clear error messages
    document.getElementById("userNameError").textContent = "";
    document.getElementById("userEmailError").textContent = "";
    document.getElementById("eventSelectError").textContent = "";
    document.getElementById("numberOfTicketsError").textContent = "";

    // Show loading spinner
    document.getElementById("loadingSpinner").style.display = "block";

    // Module 9: Simulate async operation with setTimeout
    setTimeout(() => {
        submitRegistration(userName, userEmail, eventId, numberOfTickets);
        document.getElementById("loadingSpinner").style.display = "none";
    }, 1500);
}

// Form validation function
function validateRegistrationForm(userName, userEmail, eventId, numberOfTickets) {
    const errors = {};

    if (!userName || userName.length < 2) {
        errors.userName = "😊 Please tell us your name (at least 2 characters)";
    }

    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!userEmail || !emailRegex.test(userEmail)) {
        errors.userEmail = "📧 Please enter a valid email address";
    }

    if (!eventId || eventId <= 0) {
        errors.eventSelect = "🎉 Please select an event you'd like to attend";
    }

    if (numberOfTickets < 1 || numberOfTickets > 5) {
        errors.numberOfTickets = "👥 You can register 1-5 people per registration";
    }

    return errors;
}

// Display form errors inline
function displayFormErrors(errors) {
    Object.keys(errors).forEach(fieldName => {
        const errorElement = document.getElementById(`${fieldName}Error`);
        if (errorElement) {
            errorElement.textContent = errors[fieldName];
        }
    });
}

// ===================================================
// MODULE 12: AJAX & FETCH API
// ===================================================

// Module 12: Submit registration using fetch (with mock endpoint)
function submitRegistration(userName, userEmail, eventId, numberOfTickets) {
    try {
        // Register user in the system
        const registration = registerUser(eventId, userName, userEmail, numberOfTickets);

        // Module 12: Use fetch() to POST user data to mock API
        const payload = {
            name: userName,
            email: userEmail,
            eventId: eventId,
            tickets: numberOfTickets,
            timestamp: new Date().toISOString(),
        };

        // Simulate fetch with success
        simulateApiCall(payload)
            .then(response => {
                console.log("API Response:", response);
                displaySuccessMessage();
                document.getElementById("registrationForm").reset();
                updateEventDropdown();
                renderRegistrations();
                renderEvents(eventsList);
            })
            .catch(error => {
                console.error("API Error:", error);
                displayErrorMessage(error.message);
            });
    } catch (error) {
        console.error("Registration Error:", error);
        displayErrorMessage(error.message);
    }
}

// Module 12: Mock API call using Fetch API pattern (simulated)
function simulateApiCall(payload) {
    return new Promise((resolve, reject) => {
        // Module 9: setTimeout to simulate delayed response
        setTimeout(() => {
            // Simulate successful response (80% success rate)
            if (Math.random() > 0.2) {
                resolve({
                    success: true,
                    message: "Registration successful",
                    data: payload,
                    confirmationId: "CONF-" + Math.random().toString(36).substr(2, 9).toUpperCase(),
                });
            } else {
                reject(new Error("Server error. Please try again later."));
            }
        }, 1000);
    });
}

// Display success message
function displaySuccessMessage() {
    const successMessage = document.getElementById("successMessage");
    successMessage.style.display = "block";
    successMessage.scrollIntoView({ behavior: "smooth" });
    setTimeout(() => {
        successMessage.style.display = "none";
    }, 5000);
}

// Display error message
function displayErrorMessage(message) {
    const errorMessage = document.getElementById("errorMessage");
    errorMessage.innerHTML = `⚠️ <strong>Oops!</strong> ${message}`;
    errorMessage.style.display = "block";
    errorMessage.scrollIntoView({ behavior: "smooth" });
    setTimeout(() => {
        errorMessage.style.display = "none";
    }, 5000);
}

// ===================================================
// MODULE 9: ASYNC JS, PROMISES, ASYNC/AWAIT
// ===================================================

// Module 9: Async/await version for fetching events (if using real API)
async function fetchEventsFromApi() {
    try {
        // Show loading spinner
        console.log("Fetching events from API...");

        // Simulate API delay
        const response = await new Promise((resolve, reject) => {
            setTimeout(() => {
                resolve({
                    ok: true,
                    status: 200,
                    json: async () => ({ events: eventsList }),
                });
            }, 1500);
        });

        if (response.ok) {
            const data = await response.json();
            console.log("Events loaded successfully:", data.events.length);
            return data.events;
        } else {
            throw new Error("Failed to fetch events");
        }
    } catch (error) {
        console.error("Error fetching events:", error);
        return eventsList; // Fallback to local data
    }
}

// ===================================================
// MODULE 10: MODERN JAVASCRIPT FEATURES (ES6+)
// ===================================================

// Module 10: Using let, const, default parameters
function getEventsByCategory(category = "all", sortBy = "date") {
    let filtered;

    // Module 10: Destructuring in arrow functions
    filtered = eventsList.filter(({ category: cat }) => cat === category || category === "all");

    return filtered;
}

// Module 10: Destructuring to extract event details
function displayEventInfo(event) {
    const { name, date, location, category, availableSeats } = event;
    return `${name} on ${date} at ${location} (${category}) - ${availableSeats} seats`;
}

// ===================================================
// MODULE 13: DEBUGGING AND TESTING
// ===================================================

// Debugging helper: Log detailed info
function debugLog(title, data) {
    console.group(`🐛 DEBUG: ${title}`);
    console.log(data);
    console.groupEnd();
}

// Module 13: Comprehensive testing and debugging info
function displayDebugInfo() {
    let debugOutput = "=== COMMUNITY PORTAL DEBUG INFO ===\n\n";

    debugOutput += "📊 EVENTS STATISTICS:\n";
    debugOutput += `Total Events: ${eventsList.length}\n`;
    debugOutput += `Total Registrations: ${userRegistrations.length}\n`;
    debugOutput += `Available Events: ${getAvailableEvents().length}\n\n`;

    debugOutput += "📈 REGISTRATIONS BY CATEGORY:\n";
    const categoryTotals = registrationTracker.getAllTotals();
    Object.entries(categoryTotals).forEach(([category, count]) => {
        debugOutput += `${category}: ${count}\n`;
    });
    debugOutput += "\n";

    debugOutput += "📋 EVENT DETAILS:\n";
    eventsList.forEach(event => {
        debugOutput += `- ${event.name}: ${event.availableSeats}/${event.availableSeats + event.registeredUsers} seats\n`;
    });
    debugOutput += "\n";

    debugOutput += "👥 USER REGISTRATIONS:\n";
    userRegistrations.forEach(reg => {
        debugOutput += `- ${reg.userName} → ${reg.eventName} (${reg.numberOfTickets} tickets)\n`;
    });

    document.getElementById("debugInfo").textContent = debugOutput;
    console.log(debugOutput);
}

// ===================================================
// MODULE 14: JQUERY ALTERNATIVES & FRAMEWORKS
// ===================================================

/*
jQuery Alternative Examples (commented out - jQuery not included):

// Traditional jQuery way:
$('#registerBtn').click(function() { ... });
$('.event-card').fadeIn();
$('.event-card').fadeOut();

Modern JavaScript equivalent (used in this project):
document.getElementById('registerBtn').addEventListener('click', function() { ... });
card.style.opacity = '1'; // or use CSS transitions
card.style.opacity = '0'; // or use CSS transitions

Benefits of moving to frameworks like React or Vue:
1. Component-based architecture for reusability
2. Reactive data binding - UI updates automatically when data changes
3. Virtual DOM for better performance with large datasets
4. Built-in state management and routing
5. Better testing capabilities
6. Larger ecosystem and community support
7. Cleaner, more maintainable code structure
*/

// ===================================================
// UTILITY FUNCTIONS
// ===================================================

// Check if event date is in the future
function isEventUpcoming(dateString) {
    const eventDate = new Date(dateString);
    return eventDate > new Date();
}

// Cancel registration
function cancelRegistration(registrationId) {
    const index = userRegistrations.findIndex(r => r.id === registrationId);
    if (index > -1) {
        const registration = userRegistrations[index];
        const event = eventsList.find(e => e.id === registration.eventId);
        
        if (confirm(`😢 Are you sure you want to cancel your booking for "${registration.eventName}"?\n\nWe'd love to see you there! 💔`)) {
            // Restore seats
            event.availableSeats += registration.numberOfTickets;
            event.registeredUsers -= registration.numberOfTickets;
            
            // Remove registration
            userRegistrations.splice(index, 1);
            
            console.log(`Registration ${registrationId} cancelled`);
            alert("✅ Booking cancelled. We hope to see you at another event soon! 👋");
            renderRegistrations();
            renderEvents(eventsList);
            updateEventDropdown();
        }
    }
}

// Update event dropdown in form
function updateEventDropdown() {
    const selectElement = document.getElementById("eventSelect");
    selectElement.innerHTML = '<option value="">🎯 Choose an event you\'d love to attend...</option>';

    const availableEvents = getAvailableEvents();
    
    if (availableEvents.length === 0) {
        selectElement.innerHTML += '<option disabled>😞 No available events right now</option>';
        return;
    }

    availableEvents.forEach(event => {
        const option = document.createElement("option");
        option.value = event.id;
        const spotsText = event.availableSeats === 1 ? "spot" : "spots";
        option.textContent = `${event.name} (${event.availableSeats} ${spotsText} left) - ${event.date}`;
        selectElement.appendChild(option);
    });
}

// ===================================================
// PAGE INITIALIZATION
// ===================================================

document.addEventListener("DOMContentLoaded", function () {
    console.log("DOM Content Loaded - Initializing Portal...");

    // Setup event listeners (Module 8)
    setupEventListeners();

    // Render initial events (Module 7)
    renderEvents();

    // Update event dropdown (Module 11)
    updateEventDropdown();

    // Display debug info (Module 13)
    displayDebugInfo();

    // Log sample data for debugging
    console.group("📊 Sample Event Details (Module 5 - Objects & Prototypes)");
    eventsList.slice(0, 3).forEach(event => {
        displayEventDetails(event);
    });
    console.groupEnd();

    // Log available events
    console.log("📋 Available Events (Module 6 - Arrays):", getFormattedEventCards().slice(0, 3));

    // Log closure example
    console.log("🔄 Registration Tracker (Module 4 - Closures):", registrationTracker.getAllTotals());

    console.log("✅ Community Portal Ready!");
});

// ===================================================
// DEMO/TEST FUNCTIONS (optional - call from console)
// ===================================================

// Add a test event dynamically
function testAddEvent() {
    addEvent(
        "Tech Meetup",
        "2026-09-01",
        "workshop",
        "downtown",
        "Discuss the latest tech trends",
        "💻",
        120
    );
    renderEvents();
    updateEventDropdown();
}

// Test registration
function testRegistration() {
    try {
        const registration = registerUser(1, "John Doe", "john@example.com", 2);
        console.log("Test Registration:", registration);
        renderEvents();
        renderRegistrations();
        updateEventDropdown();
    } catch (error) {
        console.error("Test Registration Failed:", error);
    }
}

// Test async/await
function testAsyncAwait() {
    console.log("Testing async/await...");
    fetchEventsFromApi().then(events => {
        console.log("Async Events Loaded:", events.length);
    });
}

console.log("💡 Try these commands in the console:");
console.log("  - testAddEvent()");
console.log("  - testRegistration()");
console.log("  - testAsyncAwait()");
console.log("  - displayDebugInfo()");
console.log("  - getMusicEvents()");
console.log("  - getAvailableEvents()");
