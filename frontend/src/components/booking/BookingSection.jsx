import { useState } from "react";

import Container from "@/components/layout/Container";
import SectionTitle from "@/components/ui/SectionTitle";

import DateSelector from "./DateSelector";
import SportSelector from "./SportSelector";
import SlotList from "./SlotList";



function BookingSection() {
    const [selectedSport, setSelectedSport] = useState("Cricket");

const [selectedDate, setSelectedDate] = useState(new Date());

    return (

        <section className="py-20">

            <Container>

                <SectionTitle

                    title="Book Your Turf"

                    subtitle="Select a date, choose your sport, and reserve your slot."

                />

                <DateSelector
    selectedDate={selectedDate}
    setSelectedDate={setSelectedDate}
                />

                <SportSelector
    selectedSport={selectedSport}
    setSelectedSport={setSelectedSport}
/>
            <div className="mt-8 p-4 bg-green-50 rounded-xl">
  <p>
    <strong>Selected Sport:</strong> {selectedSport}
  </p>

  <p>
    <strong>Selected Date:</strong>{" "}
    {selectedDate.toDateString()}
  </p>
</div>

                <div className="mt-10">

                    <SlotList
  selectedDate={selectedDate}
  selectedSport={selectedSport}
/>

                </div>

            </Container>

        </section>

    );

}

export default BookingSection;