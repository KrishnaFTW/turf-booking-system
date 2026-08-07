import {
    Calendar,
    Trophy,
    Clock3,
} from "lucide-react";

function HeroStats() {
  return (
    <div className="mt-16 grid grid-cols-3 gap-8 text-center">

      <div>
        <Calendar
    className="mx-auto mb-3 text-green-400"
    size={40}
/>

<h2 className="text-3xl font-bold text-white">

500+

</h2>

        <p className="text-gray-300">
          Bookings
        </p>
      </div>

      <div>
        <Trophy
    className="mx-auto mb-3 text-green-400"
    size={40}
/>

<h2 className="text-3xl font-bold text-white">

2

</h2>

        <p className="text-gray-300">
          Sports
        </p>
      </div>

      <div>
        <Clock3
    className="mx-auto mb-3 text-green-400"
    size={40}
/>

<h2 className="text-3xl font-bold text-white">

24x7

</h2>

        <p className="text-gray-300">
          Booking
        </p>
      </div>

    </div>
  );
}

export default HeroStats;