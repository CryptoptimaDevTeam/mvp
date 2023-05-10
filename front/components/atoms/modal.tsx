import React from "react";
import { IoClose } from "react-icons/io5";

type PropsWithChildren<P> = P & { children?: React.ReactNode | undefined };
interface ModalProps {
  isOpen: boolean;
  setIsOpen: React.Dispatch<React.SetStateAction<boolean>>;
  modalWidth?: string;
}
export type { ModalProps };

export const Modal = ({
  isOpen,
  setIsOpen,
  children,
  modalWidth,
}: PropsWithChildren<ModalProps>) => {
  const closeModalHandler = () => {
    setIsOpen(!isOpen);
  };

  return (
    <div className={`modal-container z-50 ${isOpen ? "visible" : "invisible"}`}>
      <div
        className={`modal-backdrop transition-all duration-[400ms] ${
          isOpen ? "opacity-100	" : "opacity-0"
        } inset-0 z-50 left-1/2 translate-x-[-50%] fixed w-full bg-[rgba(0,0,0,.8)] flex justify-center items-center`}
        onClick={closeModalHandler}
      >
        <div
          className={`modal-view ${
            modalWidth ? modalWidth : "w-[500px]"
          } bg-white rounded-lg p-5 shadow `}
          onClick={(e) => e.stopPropagation()}
        >
          <div className="modal-veiw-close-button h-5 flex justify-end items-center">
            <IoClose
              className="cursor-pointer p-2.5 -mr-2.5 -mt-2.5 z-[3]"
              size="36"
              onClick={closeModalHandler}
            />
          </div>
          <div className="modal-content pt-5 pb-5">{children}</div>
        </div>
      </div>
    </div>
  );
};
