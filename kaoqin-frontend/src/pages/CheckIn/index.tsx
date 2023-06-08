import type {ProFormColumnsType} from '@ant-design/pro-components';
import {BetaSchemaForm, PageContainer} from '@ant-design/pro-components';
import {message} from "antd";
import {addAttendanceUsingPOST} from "@/services/kaoqin/attendanceController";

const valueEnum = {
  '正常': {text: '正常', status: 'Default'},
  '迟到': {text: '迟到', status: 'Error'},
  '缺勤': {text: '缺勤', status: 'Success'},
  '请假': {text: '请假', status: 'Processing'},
};

const handleAdd = async (fields: API.AttendanceAddRequest) => {
  const hide = message.loading('正在添加');
  try {
    await addAttendanceUsingPOST({
      ...fields,
    });
    hide();
    message.success('创建成功');
    return true;
  } catch (error: any) {
    hide();
    message.error('创建失败，' + error.message);
    return false;
  }
};


const columns: ProFormColumnsType<API.AttendanceAddRequest>[] = [
  {
    title: '员工账号',
    dataIndex: 'employeeAccount',
    initialValue: '必填',
    formItemProps: {
      rules: [
        {
          required: true,
          message: '此项为必填项',
        },
      ],
    },
    width: 'm',
  },
  {
    title: '员工姓名',
    dataIndex: 'name',
    initialValue: '必填',
    formItemProps: {
      rules: [
        {
          required: true,
          message: '此项为必填项',
        },
      ],
    },
    width: 'm',
  },
  {
    title: '到勤情况',
    dataIndex: 'attendanceType',
    valueType: 'select',
    initialValue: '正常',
    valueEnum,
    width: 'm',
    tooltip: '每天只能选择一种情况进行签到',
  },
  {
    title: '考勤时间',
    dataIndex: 'attendanceDate',
    valueType: 'date',
    width: 'm',
  },
];

export default () => {
  return (
    <PageContainer
      title={"员工考勤"}
    >
      <BetaSchemaForm<API.AttendanceAddRequest>
        layoutType="Form"
        onFinish={async (values) => {
          await handleAdd(values);
        }}
        columns={columns}
      />
    </PageContainer>
  );
};
