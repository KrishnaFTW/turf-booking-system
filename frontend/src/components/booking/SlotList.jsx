import SlotCard from "./SlotCard";

import slots from "@/data/slots";

function SlotList() {

  return (

    <div className="space-y-4">

      {slots.map((slot) => (

        <SlotCard

          key={slot.id}

          slot={slot}

        />

      ))}

    </div>

  );

}

export default SlotList;