const API_BASE_URL = "http://localhost:8080/api";

export async function getSports() {
    const response = await fetch(`${API_BASE_URL}/sports`);

    if (!response.ok) {
        throw new Error("Failed to fetch sports");
    }

    return response.json();
}

export async function getTurfs() {
    const response = await fetch(`${API_BASE_URL}/turfs`);

    if (!response.ok) {
        throw new Error("Failed to fetch turfs");
    }

    return response.json();
}

export async function getSlots() {
    const response = await fetch(`${API_BASE_URL}/slots`);

    if (!response.ok) {
        throw new Error("Failed to fetch slots");
    }

    return response.json();
}

export async function createBooking(
    slotId,
    customerName,
    customerPhone,
    customerEmail
) {
    const params = new URLSearchParams({
        slotId: slotId,
        customerName: customerName,
        customerPhone: customerPhone,
        customerEmail: customerEmail
    });

    const response = await fetch(
        `${API_BASE_URL}/bookings?${params.toString()}`,
        {
            method: "POST"
        }
    );

    if (!response.ok) {
        throw new Error("Failed to create booking");
    }

    return response.json();
}

export async function createPayment(bookingId) {
    const response = await fetch(
        `${API_BASE_URL}/payments/create?bookingId=${bookingId}`,
        {
            method: "POST"
        }
    );

    if (!response.ok) {
        throw new Error("Failed to create payment");
    }

    return response.json();
}

export async function confirmPayment(paymentId) {
    const response = await fetch(
        `${API_BASE_URL}/payments/${paymentId}/confirm`,
        {
            method: "POST"
        }
    );

    if (!response.ok) {
        throw new Error("Failed to confirm payment");
    }

    return response.json();
}