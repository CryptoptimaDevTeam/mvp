import React, { useState } from "react";
import { RiArrowDownSLine } from "react-icons/ri";

type PropsWithChildren<P> = P & { children?: React.ReactNode | undefined };
interface HomeDropdownPropsType {
  order: number;
  title: string;
  body: string;
}

export const HomeDropdown = ({
  order,
  title,
  body,
}: PropsWithChildren<HomeDropdownPropsType>) => {
  const [isOpen, setIsOpen] = useState(false);

  return (
    <li
      className="question-dropdown border-[1px] border-borderColor p-8 cursor-pointer rounded-xl w-[860px] flex flex-col gap-2.5"
      onClick={() => {
        setIsOpen(!isOpen);
      }}
    >
      <div className="question-wrapper flex justify-between items-center">
        <div className="question-title font-medium">{title}</div>
        <div className="question-arrow">
          <RiArrowDownSLine
            className={`${isOpen ? "scale-[-1]" : ""} duration-500`}
            size={20}
          />
        </div>
      </div>
      {isOpen && <div className="answer text-[#72717d]">{body}</div>}
    </li>
  );
};
