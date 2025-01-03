import React from 'react';

const Button = ({ button, ...rest }) => {
  const fixedStyles =
    'bg-purple-600 text-white font-bold hover:bg-purple-900 h-10 px-4 rounded-md text-sm sm:text-base lg:text-lg lg:px-6 transition duration-300 ease-in-out';

  return (
    <button className={fixedStyles} {...rest}>
      {button}
    </button>
  );
};

export default Button