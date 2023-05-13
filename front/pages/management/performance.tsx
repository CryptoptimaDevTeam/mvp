import type { ReactElement } from "react";
import ManageLayout from "../../layout/manageLayout";

const ManagePerformance = () => {
  return <div></div>;
};

export default ManagePerformance;

ManagePerformance.getLayout = function getLayout(page: ReactElement) {
  return <ManageLayout>{page}</ManageLayout>;
};
