import Card from "@/components/ui/Card";
import Button from "@/components/ui/Button";
import SlotStatusBadge from "./SlotStatusBadge";

import { getSlotTimeStatus } from "@/utils/slotUtils";

function SlotCard({
  slot,
  selectedDate,
  onSelectSlot,
}) {
  const timeStatus = getSlotTimeStatus(
    slot,
    selectedDate
  );

  const isBooked = slot.status === "BOOKED";

  const canBook =
    !isBooked &&
    timeStatus !== "EXPIRED" &&
    timeStatus !== "ACTIVE";

  return (
    <Card className="flex flex-col md:flex-row md:items-center md:justify-between gap-5">

      <div>
        <h3 className="font-bold text-lg">
          {slot.startTime} - {slot.endTime}
        </h3>

        <p className="text-gray-500">
          {slot.sport}
        </p>
      </div>

      <div>
        <p className="font-bold text-xl">
          ₹{slot.price}
        </p>
      </div>

      <div>
        {isBooked ? (
          <SlotStatusBadge status="BOOKED" />
        ) : (
          <SlotStatusBadge status={timeStatus} />
        )}
      </div>

      <div>
        {canBook ? (
          <Button
            onClick={() => onSelectSlot(slot)}
          >
            Book
          </Button>
        ) : (
          <Button
            disabled
            className="opacity-50 cursor-not-allowed"
          >
            {isBooked
              ? "Booked"
              : timeStatus === "ACTIVE"
              ? "Currently Playing"
              : "Expired"}
          </Button>
        )}
      </div>

    </Card>
  );
}

export default SlotCard;