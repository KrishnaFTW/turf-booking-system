import Container from "@/components/layout/Container";
import SectionTitle from "@/components/ui/SectionTitle";
import SlotList from "@/components/booking/SlotList";

function LiveSlotSection() {

  return (

    <section className="py-20 bg-gray-50">

      <Container>

        <SectionTitle

          title="Today's Available Slots"

          subtitle="Check live availability before booking."

        />

        <SlotList />

      </Container>

    </section>

  );

}

export default LiveSlotSection;