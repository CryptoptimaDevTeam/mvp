import { useEffect, useState } from "react";
import type { ReactElement } from "react";
import ManageLayout from "../../layout/manageLayout";
import { FiEdit2, FiTrash2 } from "react-icons/fi";
import { useForm, SubmitHandler } from "react-hook-form";
import { Modal } from "../../components/atoms/modal";
import { MainButton, SubButton } from "../../components/atoms/button";

import { managerAccountData } from "../../data/test/manageAccount";

interface AccountEditType {
  managerId: string;
  managerName: string;
  managerInfo: string;
}

const ManageAccount = () => {
  const [editTarget, setEditTarget] = useState<AccountEditType>({
    managerId: "",
    managerName: "",
    managerInfo: "",
  });
  const [editModeOpen, setEditModeOpen] = useState(false);
  const { register, handleSubmit, getValues, setFocus, reset } =
    useForm<AccountEditType>();

  const [deleteTarget, setDeleteTarget] = useState("none");
  const [deleteModalOpen, setDeleteModalOpen] = useState(false);

  const EditModeHandle = (data: AccountEditType) => {
    if (editModeOpen) {
      setEditTarget({ managerId: "", managerName: "", managerInfo: "" });
      setEditModeOpen(false);
    } else {
      setEditTarget(data);
      setEditModeOpen(true);
    }
  };

  const EditHandle = (data: AccountEditType) => {
    if (
      editTarget.managerId === data.managerId &&
      editTarget.managerName === data.managerName &&
      editTarget.managerInfo === data.managerInfo
    ) {
      EditModeHandle(data);
    } else {
      console.log("변경사항 있다!");
      // edit 관련 api 통신
    }
  };

  const DeleteHandle = (managerId: string) => {
    setDeleteModalOpen(!deleteModalOpen);
    setDeleteTarget(managerId);
  };

  return (
    <div className="manage-account-container border-[1px] border-borderColor rounded-md w-[1248px] mt-10 mx-auto">
      <div className="manage-account-wrapper">
        <div className="manage-account-title flex justify-center py-5 border-b-[1px] border-borderColor gap-2 font-bold">
          <div className="manager-id-title flex-[1_1_0%] text-center">
            Manager ID
          </div>
          <div className="manager-name-title flex-[1_1_0%] text-center">
            Name
          </div>
          <div className="manager-info-title flex-[3_1_0%] text-center">
            Info
          </div>
          <div className="manager-grade-title flex-[1_1_0%] text-center">
            Grade
          </div>
          <div className="btn-arae flex items-center flex-[1_1_0%] text-center">
            <div className="edit-btn-title flex-[1_1_0%]">Edit</div>
            <div className="delete-btn-title flex-[1_1_0%]">Delete</div>
          </div>
        </div>
        <ul className="manage-account-list flex flex-col">
          {managerAccountData.map((el, idx) => {
            return (
              <li
                className={`${idx % 2 !== 0 && "bg-[#f5f5f5]"}`}
                key={el.managerId}
              >
                <form
                  className="flex items-center gap-2 text-sm h-[65px] py-5"
                  onSubmit={handleSubmit((data) => {
                    EditHandle(data);
                  })}
                >
                  {editTarget.managerId === el.managerId ? (
                    <>
                      <div className="flex-[1_1_0%] flex justify-center">
                        <input
                          className="manager-id text-center w-[90%] rounded border-[1px] border-borderColor py-1"
                          type="text"
                          defaultValue={editTarget.managerId}
                          {...register("managerId")}
                        />
                      </div>
                      <div className="flex-[1_1_0%] flex justify-center">
                        <input
                          className="manager-name text-center w-[90%] rounded border-[1px] border-borderColor py-1"
                          type="text"
                          defaultValue={editTarget.managerName}
                          {...register("managerName")}
                        />
                      </div>
                      <div className="flex-[3_1_0%] flex justify-center">
                        <input
                          className="manager-info text-center w-[90%] rounded border-[1px] border-borderColor py-1"
                          type="text"
                          defaultValue={editTarget.managerInfo}
                          {...register("managerInfo")}
                        />
                      </div>
                    </>
                  ) : (
                    <>
                      <div className="manager-id flex-[1_1_0%] text-center">
                        {el.managerId}
                      </div>
                      <div className="manager-name flex-[1_1_0%] text-center">
                        {el.managerName}
                      </div>
                      <div className="manager-info flex-[3_1_0%] text-center">
                        {el.managerInfo}
                      </div>
                    </>
                  )}
                  <div className="manager-grade flex-[1_1_0%] text-center">
                    {el.manaGrade}
                  </div>
                  <div className="btn-area flex items-center flex-[1_1_0%] justify-center">
                    <div className="edit-btn flex-[1_1_0%] flex justify-center">
                      {editTarget.managerId === el.managerId ? (
                        <button className="cursor-pointer" type="submit">
                          Edit
                        </button>
                      ) : (
                        <FiEdit2
                          className="cursor-pointer"
                          onClick={() => {
                            editModeOpen
                              ? alert("진행중인 편집을 마쳐주세요.")
                              : EditModeHandle({
                                  managerId: el.managerId,
                                  managerName: el.managerName,
                                  managerInfo: el.managerInfo,
                                });
                          }}
                        />
                      )}
                    </div>
                    <div className="delete-btn flex-[1_1_0%] flex justify-center">
                      <FiTrash2
                        className="cursor-pointer"
                        onClick={() => DeleteHandle(el.managerId)}
                      />
                    </div>
                  </div>
                </form>
              </li>
            );
          })}
        </ul>
      </div>
      <div className="pagination-wrapper border-t-[1px] border-borderColor invisible"></div>
      <Modal isOpen={deleteModalOpen} setIsOpen={setDeleteModalOpen}>
        <div className="flex flex-col justify-center items-center">
          <div>Are you sure to delete this account?</div>
          <div className="font-semibold text-mainColor">
            Manager ID : {deleteTarget}
          </div>
          <div className="btn-wrapper flex justify-center items-center gap-2.5 w-full pt-10">
            <SubButton
              name="Cancel"
              style="w-full"
              onClick={() => {
                setDeleteModalOpen(!deleteModalOpen);
              }}
            />
            <MainButton name="Delete" style="w-full" onClick={() => {}} />
          </div>
        </div>
      </Modal>
    </div>
  );
};

export default ManageAccount;

ManageAccount.getLayout = function getLayout(page: ReactElement) {
  return <ManageLayout>{page}</ManageLayout>;
};
