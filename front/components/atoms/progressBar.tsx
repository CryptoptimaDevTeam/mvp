import { useState, useEffect } from "react";
import { styled } from "styled-components";

interface ProgressBarPropsType {
  progressStatus: number;
}

export const ProgressBar = ({ progressStatus }: ProgressBarPropsType) => {
  return (
    <div className="progress-bar-wrapper bg-transparent h-1 w-full">
      <ProgressFill
        className="progress-bar-fill"
        style={{ width: `${progressStatus}%` }}
      ></ProgressFill>
    </div>
  );
};

const ProgressFill = styled.div`
  background-color: #1f4ded;
  height: 100%;
  width: 0%;
  transition: width 0.5s ease-in-out;
`;
