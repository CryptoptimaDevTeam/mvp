import { useState } from "react";
import { useForm, SubmitHandler } from "react-hook-form";
import type { ReactElement } from "react";
import NoLayout from "../../layout/noLayout";
import Image from "next/image";
import { AiFillEyeInvisible, AiFillEye } from "react-icons/ai";
import { MainButton } from "../../components/atoms/button";

interface LoginFormType {
  id: string;
  pw: string;
}

const ManageLogin = () => {
  const { register, handleSubmit, getValues, setFocus, reset } =
    useForm<LoginFormType>();
  const [passwordVisible, setPasswordVisible] = useState(false);

  return (
    <div className="max-w-[1248px] min-h-screen mx-auto flex flex-col items-center justify-center">
      <div className="logo-area max-w-[400px]">
        <Image
          src="/image/logo/logo_full_l.svg"
          alt="logo"
          width={1000}
          height={200}
        />
      </div>
      <div className="login-area w-[400px] pt-[60px]">
        <form className="flex flex-col gap-5">
          <div className="account-id flex">
            <label
              htmlFor="id"
              className="flex justify-center items-center w-[80px]"
            >
              아이디
            </label>
            <input
              id="id"
              className="flex-[1_1_0%] p-[5px] border-[1px] rounded border-borderColor"
              type="text"
            ></input>
          </div>
          <div className="account-pw flex relative">
            <label
              htmlFor="pw"
              className="flex justify-center items-center w-[80px]"
            >
              비밀번호
            </label>
            <input
              id="pw"
              className="flex-[1_1_0%] p-[5px] border-[1px] rounded border-borderColor pr-7"
              type={passwordVisible ? "text" : "password"}
            ></input>
            <div
              className="pw-visible absolute top-[11px] right-2"
              onClick={() => {
                setPasswordVisible(!passwordVisible);
              }}
            >
              {passwordVisible ? <AiFillEyeInvisible /> : <AiFillEye />}
            </div>
          </div>
          <div className="login-btn flex justify-center items-center pt-5">
            <MainButton
              name="로그인"
              hoverBg={true}
              hoverScale={true}
              style="w-1/2"
              onClick={() => {}}
            />
          </div>
        </form>
      </div>
    </div>
  );
};

export default ManageLogin;

ManageLogin.getLayout = function getLayout(page: ReactElement) {
  return <NoLayout>{page}</NoLayout>;
};
