function Button({

    children,

    variant = "primary",

    type = "button",

    className = "",

    ...props

}) {

    const styles = {

        primary:
            "bg-green-600 hover:bg-green-700 text-white",

        secondary:
            "bg-blue-600 hover:bg-blue-700 text-white",

        outline:
            "border border-green-600 text-green-600 hover:bg-green-600 hover:text-white",

    };

    return (

        <button

            type={type}

            className={`

                px-6

                py-3

                rounded-xl

                font-semibold

                transition-all

                duration-300

                ${styles[variant]}

                ${className}

            `}

            {...props}

        >

            {children}

        </button>

    );

}

export default Button;