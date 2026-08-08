const getDateKey = (date) => {
  const year = date.getFullYear();
  const month = String(date.getMonth() + 1).padStart(2, "0");
  const day = String(date.getDate()).padStart(2, "0");

  return `${year}-${month}-${day}`;
};

const generateSlots = () => {
  const slots = [];

  const timeSlots = [
    {
      startTime: "06:00 AM",
      endTime: "07:00 AM",
      hour: 6,
    },
    {
      startTime: "07:00 AM",
      endTime: "08:00 AM",
      hour: 7,
    },
    {
      startTime: "08:00 AM",
      endTime: "09:00 AM",
      hour: 8,
    },
    {
      startTime: "09:00 AM",
      endTime: "10:00 AM",
      hour: 9,
    },
    {
      startTime: "05:00 PM",
      endTime: "06:00 PM",
      hour: 17,
    },
    {
      startTime: "06:00 PM",
      endTime: "07:00 PM",
      hour: 18,
    },
    {
      startTime: "07:00 PM",
      endTime: "08:00 PM",
      hour: 19,
    },
    {
      startTime: "08:00 PM",
      endTime: "09:00 PM",
      hour: 20,
    },
  ];

  const sports = ["Cricket", "Football"];

  for (let day = 0; day < 4; day++) {
    const date = new Date();

    date.setDate(date.getDate() + day);

    const dateKey = getDateKey(date);

    sports.forEach((sport) => {
      timeSlots.forEach((timeSlot, index) => {
        slots.push({
          id: `${dateKey}-${sport}-${index}`,

          date: dateKey,

          sport: sport,

          startTime: timeSlot.startTime,

          endTime: timeSlot.endTime,

          hour: timeSlot.hour,

          price: timeSlot.hour >= 17 ? 800 : 600,

          status:
            index === 1 || index === 6
              ? "BOOKED"
              : "AVAILABLE",
        });
      });
    });
  }

  return slots;
};

const slots = generateSlots();

export default slots;