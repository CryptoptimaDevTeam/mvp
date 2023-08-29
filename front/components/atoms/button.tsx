import React from "react";

interface ButtonPropsType {
  onClick: () => void;
  hoverScale?: boolean;
  hoverBg?: boolean;
  name: string;
  style?: string;
}

export const MainButton: React.FC<ButtonPropsType> = (props) => {
  return (
    <button
      className={`bg-mainColor text-white w-full py-2 rounded-lg transition-all ${
        props.style && props.style
      } ${props.hoverScale && "hover:scale-[1.03]"} ${
        props.hoverBg && "hover:bg-mainUpColor"
      }`}
      onClick={() => props.onClick()}
    >
      {props.name}
    </button>
  );
};

export const SubButton: React.FC<ButtonPropsType> = (props) => {
  return (
    <button
      className={`bg-white border-[1px] border-mainColor text-mainColor w-full py-2 rounded-lg transition-all ${
        props.style && props.style
      } ${props.hoverScale && "hover:scale-[1.03]"} ${
        props.hoverBg && "hover:bg-mainUpColor"
      }`}
      onClick={() => props.onClick()}
    >
      {props.name}
    </button>
  );
};
