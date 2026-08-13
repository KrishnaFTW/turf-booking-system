export function getSlotTimeStatus(slot, selectedDate) {
  const today = new Date();

  const slotDate = new Date(selectedDate);

  slotDate.setHours(0, 0, 0, 0);

  const currentDate = new Date(today);

  currentDate.setHours(0, 0, 0, 0);

  if (slotDate < currentDate) {
    return "EXPIRED";
  }

  if (slotDate > currentDate) {
    return "UPCOMING";
  }

  const currentHour = today.getHours();

  if (currentHour >= slot.endHour) {
    return "EXPIRED";
  }

  if (
    currentHour >= slot.startHour &&
    currentHour < slot.endHour
  ) {
    return "ACTIVE";
  }

  return "UPCOMING";
}