import SlotCard from "./SlotCard";
import slots from "@/data/slots";

function getDateKey(date) {
  const year = date.getFullYear();
  const month = String(date.getMonth() + 1).padStart(2, "0");
  const day = String(date.getDate()).padStart(2, "0");

  return `${year}-${month}-${day}`;
}

function SlotList({ selectedDate, selectedSport, onSelectSlot }) {
  const selectedDateKey = getDateKey(selectedDate);

  const filteredSlots = slots.filter((slot) => {
    return (
      slot.date === selectedDateKey &&
      slot.sport === selectedSport
    );
  });

  if (filteredSlots.length === 0) {
    return (
      <div className="text-center py-10">
        <p className="text-gray-500">
          No slots available for the selected date and sport.
        </p>
      </div>
    );
  }

  return (
    <div className="space-y-4">
      {filteredSlots.map((slot) => (
        <SlotCard
          key={slot.id}
          slot={slot}
          onSelectSlot={onSelectSlot}
        />
      ))}
    </div>
  );
}

export default SlotList;