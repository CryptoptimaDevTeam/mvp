import type { ReactElement } from "react";
import ManageLayout from "../../layout/manageLayout";

const ManagePayback = () => {
  return <div></div>;
};

export default ManagePayback;

ManagePayback.getLayout = function getLayout(page: ReactElement) {
  return <ManageLayout>{page}</ManageLayout>;
};
