import React from "react";

interface ButtonPropsType {
  onClick: () => void;
  hoverScale?: boolean;
  hoverBg?: boolean;
  name: string;
  style?: string;
  disabled?: boolean;
  className?: string;
}

export const MainButton: React.FC<ButtonPropsType> = (props) => {
  return (
    <button
      className={`bg-mainColor text-white w-full py-2 rounded-lg transition-all ${
        props.style && props.style
      } ${props.className ? props.className : ""} ${
        props.hoverScale && "hover:scale-[1.03]"
      } ${props.hoverBg && "hover:bg-mainUpColor"}`}
      onClick={() => props.onClick()}
      disabled={props.disabled}
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
      } ${props.className ? props.className : ""} ${
        props.hoverScale && "hover:scale-[1.03]"
      } ${props.hoverBg && "hover:bg-mainUpColor"}`}
      onClick={() => props.onClick()}
      disabled={props.disabled}
    >
      {props.name}
    </button>
  );
};

export const NormalButton: React.FC<ButtonPropsType> = (props) => {
  return (
    <button
      className={`border-[1px] w-full py-2 transition-all ${
        props.style ? props.style : ""
      } ${props.className ? props.className : ""} ${
        props.hoverScale && "hover:scale-[1.03]"
      }`}
      onClick={() => props.onClick()}
      disabled={props.disabled}
    >
      {props.name}
    </button>
  );
};
