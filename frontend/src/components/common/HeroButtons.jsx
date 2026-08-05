import Button from "../ui/Button";

function HeroButtons() {
  return (
    <div className="mt-10 flex flex-wrap gap-5">

      <Button>
        Book Now
      </Button>

      <Button variant="outline">
        View Slots
      </Button>

    </div>
  );
}

export default HeroButtons;