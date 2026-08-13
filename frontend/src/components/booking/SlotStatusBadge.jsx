function SlotStatusBadge({ status }) {
  const statusConfig = {
    AVAILABLE: {
      label: "Available",
      style: "bg-green-100 text-green-700",
    },

    UPCOMING: {
      label: "Upcoming",
      style: "bg-blue-100 text-blue-700",
    },

    ACTIVE: {
      label: "Currently Playing",
      style: "bg-orange-100 text-orange-700",
    },

    BOOKED: {
      label: "Booked",
      style: "bg-red-100 text-red-700",
    },

    EXPIRED: {
      label: "Expired",
      style: "bg-gray-100 text-gray-500",
    },
  };

  const config =
    statusConfig[status] ||
    statusConfig.AVAILABLE;

  return (
    <span
      className={`px-3 py-1 rounded-full text-sm font-semibold ${config.style}`}
    >
      {config.label}
    </span>
  );
}

export default SlotStatusBadge;