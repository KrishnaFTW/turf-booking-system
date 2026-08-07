import Button from "@/components/ui/Button";

function HeroButtons() {
  return (
    <div className="mt-10 flex flex-wrap gap-5">

      <Button>
        Book a Slot
      </Button>

      <Button variant="outline">
        Today's Availability
      </Button>

    </div>
  );
}

export default HeroButtons;