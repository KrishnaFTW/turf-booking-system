function Button({
  children,
  onClick,
  className = "",
  type = "button",
}) {
  return (
    <button
      type={type}
      onClick={onClick}
      className={`bg-green-600 hover:bg-green-700 text-white px-6 py-3 rounded-lg transition duration-300 ${className}`}
    >
      {children}
    </button>
  );
}

export default Button;