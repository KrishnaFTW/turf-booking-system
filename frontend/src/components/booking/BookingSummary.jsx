import Card from "@/components/ui/Card";
import CustomerForm from "./CustomerForm";

function BookingSummary({ slot, onBookingSubmit }) {
  if (!slot) {
    return null;
  }

  return (
    <Card className="mt-10 max-w-2xl mx-auto">
      <h2 className="text-2xl font-bold mb-6">
        Booking Summary
      </h2>

      <div className="space-y-4">
        <div className="flex justify-between">
          <span className="text-gray-500">
            Sport
          </span>

          <span className="font-semibold">
            {slot.sport}
          </span>
        </div>

        <div className="flex justify-between">
          <span className="text-gray-500">
            Date
          </span>

          <span className="font-semibold">
            {slot.date}
          </span>
        </div>

        <div className="flex justify-between">
          <span className="text-gray-500">
            Time
          </span>

          <span className="font-semibold">
            {slot.startTime} - {slot.endTime}
          </span>
        </div>

        <div className="flex justify-between border-t pt-4">
          <span className="text-lg font-semibold">
            Total
          </span>

          <span className="text-2xl font-bold text-green-600">
            ₹{slot.price}
          </span>
        </div>
      </div>

      <div className="border-t mt-6 pt-6">
        <h3 className="text-xl font-semibold">
          Customer Details
        </h3>

        <CustomerForm onSubmit={onBookingSubmit} />
      </div>
    </Card>
  );
}

export default BookingSummary;