import { useEffect, Dispatch, SetStateAction } from 'react';
import { BiBlock, BiHelpCircle } from 'react-icons/bi';

interface AlertStatusType {
  top?: string;
  isOpen: boolean;
  message: string;
  type: string;
}

interface AlertPropsType {
  alertStatus: AlertStatusType;
  setAlertStatus: Dispatch<SetStateAction<AlertStatusType>>;
  time?: number;
}

export function Alert({ alertStatus, setAlertStatus, time }: AlertPropsType) {
  useEffect(() => {
    setTimeout(() => {
      if (alertStatus.isOpen) {
        setAlertStatus({ ...alertStatus, isOpen: false });
      }
    }, time || 4000);
  }, [alertStatus.isOpen]);

  const alertBoxClassName = alertStatus.isOpen ? 'flex' : 'hidden';

  return (
    <div
      className={`alert-container fixed mx-auto ${
        alertStatus.top ? alertStatus.top : 'top-5'
      } justify-center left-1/2 -translate-x-1/2 z-20  ${alertBoxClassName}`}
    >
      <div
        className='alert-wrapper py-2 px-4 flex justify-center items-center gap-2 animate-dropDown 
      bg-mainColor text-white border-[1px] border-bordercolor rounded'
      >
        <div className='alert-icon flex items-center pt-[2px]'>
          {alertStatus.type === 'caution' ? (
            <BiBlock />
          ) : alertStatus.type === 'caution' ? (
            <BiHelpCircle />
          ) : (
            <></>
          )}
        </div>
        <div className='alert-message pt-[2px] t-[16px] flex items-center'>
          <span>{alertStatus.message}</span>
        </div>
      </div>
    </div>
  );
}
