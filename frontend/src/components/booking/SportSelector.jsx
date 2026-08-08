function SportSelector({
  selectedSport,
  setSelectedSport,
}) {
  return (
    <div className="flex gap-6 mt-8">
      <button
        onClick={() => setSelectedSport("Cricket")}
        className={`px-8 py-4 rounded-xl transition ${
          selectedSport === "Cricket"
            ? "bg-green-600 text-white"
            : "border"
        }`}
      >
        🏏 Cricket
      </button>

      <button
        onClick={() => setSelectedSport("Football")}
        className={`px-8 py-4 rounded-xl transition ${
          selectedSport === "Football"
            ? "bg-green-600 text-white"
            : "border"
        }`}
      >
        ⚽ Football
      </button>
    </div>
  );
}

export default SportSelector;