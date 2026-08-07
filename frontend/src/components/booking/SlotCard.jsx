import Card from "@/components/ui/Card";
import Button from "@/components/ui/Button";
import SlotStatusBadge from "./SlotStatusBadge";

function SlotCard({ slot }) {

  return (

    <Card className="flex justify-between items-center">

      <div>

        <h3 className="font-bold text-lg">

          {slot.startTime} - {slot.endTime}

        </h3>

        <p className="text-gray-500">

          {slot.sport}

        </p>

      </div>

      <div className="text-center">

        <p className="font-bold text-xl">

          ₹{slot.price}

        </p>

      </div>

      <div>

        <SlotStatusBadge status={slot.status} />

      </div>

      <div>

        {slot.status === "AVAILABLE" ? (

          <Button>

            Book

          </Button>

        ) : (

          <Button
            className="cursor-not-allowed opacity-60"
            disabled
          >
            Booked
          </Button>

        )}

      </div>

    </Card>

  );

}

export default SlotCard;