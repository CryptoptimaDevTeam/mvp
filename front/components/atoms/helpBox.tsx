import React from "react";
import { styled } from "styled-components";
import { FiHelpCircle } from "react-icons/fi";

interface HelpBoxDivPropsType {
  top?: string;
  bottom?: string;
  right?: string;
  left?: string;
  upsidedown?: "true" | "false";
}

const HelpBoxDiv = styled.div<HelpBoxDivPropsType>`
  width: max-content;
  border-radius: 5px;
  padding: 12px 12.8px;

  & > :after {
    border-top: 10px solid #f1f1f4;
    border-left: 10px solid transparent;
    border-right: 10px solid transparent;
    border-bottom: 0px solid transparent;
    content: "";
    position: absolute;
    top: ${(props) => props.top || ""};
    bottom: ${(props) => props.bottom || ""};
    right: ${(props) => props.right || ""};
    left: ${(props) => props.left || ""};
    transform: ${(props) =>
      props.upsidedown === "true" ? "rotate(180deg)" : ""};
  }
`;

interface HelpBoxProps {
  isOpen: boolean;
  text: string;
  top?: string;
  bottom?: string;
  left?: string;
  right?: string;
  upsideDown?: "true" | "false";
}

export const HelpBox = ({
  isOpen,
  text,
  top,
  bottom,
  left,
  right,
  upsideDown,
}: HelpBoxProps) => {
  const textList = text.split("|");

  return (
    <HelpBoxDiv
      className={`help-box bg-[#f1f1f4] ${isOpen ? "visible" : "invisible"}`}
      top={top}
      bottom={bottom}
      right={right}
      left={left}
      upsidedown={upsideDown}
    >
      <div className={`helpbox-wrapper flex items-center gap-2 `}>
        <div className="help-icon">
          <FiHelpCircle color="#72717d" />
        </div>
        <ul className="helpbox-text-list flex flex-col justify-center">
          {textList.map((el, idx) => (
            <li className="helpbox-text-item" key={idx}>
              <div className="text-textGrayColor text-sm">{el}</div>
            </li>
          ))}
        </ul>
      </div>
    </HelpBoxDiv>
  );
};
