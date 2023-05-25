import type { ReactElement } from "react";
import NoLayout from "../../../layout/noLayout";
import { useState } from "react";
import { RegistrationHeader } from "../../../components/blocks/registrationHeader";
import { ProgressBar } from "../../../components/atoms/progressBar";
import { MainButton, SubButton } from "../../../components/atoms/button";
import { useForm, SubmitHandler } from "react-hook-form";
import { Modal } from "../../../components/atoms/modal";
import { useRouter } from "next/router";
import { Alert } from "../../../components/atoms/alert";

interface FormValeType {
  uid: string;
}

type ModalType = "uidRegistrationCheck" | "uidGuideVideo" | "";

const RegistrationNoAccount = () => {
  const router = useRouter();
  const exchangeName =
    typeof router.query.exchangeName === "string"
      ? router.query.exchangeName
      : "";
  const progressChange: number = (1 / 3) * 100;
  const [progressStatus, setProgressStatus] = useState<number>(progressChange);
  const [userUid, setUserUid] = useState<string>("");
  const { register, handleSubmit, reset } = useForm<FormValeType>();
  const [isModalOpen, setIsModalOpen] = useState<boolean>(false);
  const [modalType, setModalType] = useState<ModalType>("");

  const registerUidHandle: SubmitHandler<FormValeType> = (data) => {
    console.log(data.uid.length);
    if (data.uid.length === 0) {
      setAlertStatus({
        isOpen: true,
        message: "Enter your UID!",
        type: "caution",
        top: "top-[90px]",
      });
    } else {
      setUserUid(data.uid);
      setModalType("uidRegistrationCheck");
      setIsModalOpen(true);
      reset();
    }
  };

  const [alertStatus, setAlertStatus] = useState({
    isOpen: false,
    message: "",
    type: "caution",
    top: "",
  });

  const homeSectionStyle: string =
    "max-w-[1248px] mx-auto py-[100px] flex justify-center items-center";

  return (
    <div>
      <Alert alertStatus={alertStatus} setAlertStatus={setAlertStatus} />
      <RegistrationHeader
        progressStatus={progressStatus}
        setProgressStatus={setProgressStatus}
        progressChange={progressChange}
      />
      <div className="progress-bar-wrapper sticky top-[70px] z-50">
        <ProgressBar progressStatus={progressStatus} />
      </div>
      {progressStatus === (1 / 3) * 100 ? (
        <section
          className={`registration-no-account-section1 ${homeSectionStyle} flex-col pt-[60px]`}
        >
          <div className="section-title text-[48px] font-bold text-center max-w-[960px] px-6">
            Create your crypto exchange account using the button below
          </div>
          <div className="section-body mt-8 text-[20px] max-w-[886px] text-[#72717d] text-center">
            To complete the registration, please make sure to{" "}
            <span className="text-mainColor">
              click the button below and sign up
            </span>
            .<br />
            Failure to do so may result in unsuccessful registration.
          </div>
          <div className="register-btn mt-10">
            <MainButton
              name="Create a Cryptocurrency Exchange Account"
              onClick={() => {
                window.open("http://www.naver.com", "_blank");
                setProgressStatus((status) => status + progressChange);
              }}
              style="py-[21px] px-[24px] w-[250px]"
              hoverScale={true}
              hoverBg={true}
            />
          </div>
        </section>
      ) : progressStatus === (2 / 3) * 100 ? (
        <section
          className={`registration-no-account-section2 ${homeSectionStyle} flex-col pt-[60px]`}
        >
          <div className="section-title text-[48px] font-bold text-center max-w-[960px] px-6">
            Enter your Exchange UID
          </div>
          <div className="uid-input-wrapper mt-10">
            <form
              className="flex justify-center items-center gap-2.5"
              onSubmit={handleSubmit(registerUidHandle)}
            >
              <input
                className="border-[1px] border-borderColor rounded-lg w-[450px] h-[56px] px-2.5 py-2.5 :focus:outline-mainColor"
                placeholder="Please enter your cryptocurrency exchange UID"
                type="text"
                {...register("uid")}
              />
              <div className="register-btn">
                <MainButton
                  name="Register UID"
                  type="submit"
                  onClick={() => {}}
                  style="py-[16px] px-[19px] w-[250px]"
                  hoverScale={true}
                  hoverBg={true}
                />
              </div>
            </form>
          </div>
          <div className="section-body mt-10 max-w-[886px] text-[#7d7d7d] text-center">
            Do you not know where to find your cryptocurrency exchange UID?{" "}
            <span
              className="text-mainColor font-semibold cursor-pointer px-2"
              onClick={() => {
                setIsModalOpen(true);
                setModalType("uidGuideVideo");
              }}
            >
              Click!
            </span>
          </div>
        </section>
      ) : (
        <section className="no-account-section3">section3</section>
      )}

      <Modal isOpen={isModalOpen} setIsOpen={setIsModalOpen}>
        <div
          className={`${
            modalType === "uidRegistrationCheck" ? "visible" : "invisible"
          }  ${
            isModalOpen ? "opacity-100	" : "opacity-0"
          } uid-registration-check-modal flex flex-col gap-7 justify-center items-center`}
        >
          <div className="uid-registration-check-modal-comment text-xl text-center font-semibold">
            Is the one below your {exchangeName.toUpperCase()} account UID?
          </div>
          <div className="uid-registration-check-modal-uid text-2xl text-mainColor font-semibold text-center">
            {userUid || "Cryptoptima"}
          </div>
          <div className="uid-registration-check-modal-btn-wrapper flex justify-center items-center gap-2.5">
            <SubButton
              className="re-enter-btn"
              name="Re-enter"
              onClick={() => {
                setIsModalOpen(false);
                setModalType("");
                setUserUid("");
              }}
              width="w-[160px]"
              hoverScale={true}
            />
            <MainButton
              className="next-btn"
              name="Right!"
              onClick={() => {
                setIsModalOpen(false);
                setModalType("");
                setProgressStatus((status) => status + progressChange);
              }}
              width="w-[160px]"
              hoverScale={true}
              hoverBg={true}
            />
          </div>
        </div>

        <div
          className={`${
            modalType === "uidGuideVideo" ? "visible" : "invisible"
          } transition-all duration-[400ms] ${
            isModalOpen ? "opacity-100	" : "opacity-0"
          } uid-find-guide-video-modal flex flex-col justify-center items-center`}
        ></div>
      </Modal>
    </div>
  );
};

export default RegistrationNoAccount;

RegistrationNoAccount.getLayout = function getLayout(page: ReactElement) {
  return <NoLayout>{page}</NoLayout>;
};
