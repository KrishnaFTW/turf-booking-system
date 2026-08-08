function DateSelector({ selectedDate, setSelectedDate }) {
  const dates = [];

  for (let i = 0; i < 4; i++) {
    const date = new Date();
    date.setDate(date.getDate() + i);

    dates.push(date);
  }

  return (
    <div className="flex gap-4 flex-wrap">
      {dates.map((date, index) => {
        const isSelected =
          date.toDateString() === selectedDate.toDateString();

        return (
          <button
            key={index}
            onClick={() => setSelectedDate(date)}
            className={`px-6 py-3 rounded-xl transition font-medium
              ${
                isSelected
                  ? "bg-green-600 text-white"
                  : "border hover:bg-green-100"
              }`}
          >
            {date.toLocaleDateString("en-IN", {
              weekday: "short",
              day: "numeric",
              month: "short",
            })}
          </button>
        );
      })}
    </div>
  );
}

export default DateSelector;