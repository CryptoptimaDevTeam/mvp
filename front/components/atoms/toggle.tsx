interface TogglePropsType {
  isOn: boolean;
  setIsOn: React.Dispatch<React.SetStateAction<boolean>>;
}

export const Toggle = ({ isOn, setIsOn }: TogglePropsType) => {
  return <div></div>;
};
