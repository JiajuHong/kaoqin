import type {ActionType, ProColumns} from '@ant-design/pro-components';
import {PageContainer, ProTable} from '@ant-design/pro-components';
import React, {useRef} from 'react';
import {getMyAttendanceUsingGET} from "@/services/kaoqin/attendanceController";

const TableList: React.FC = () => {
  const actionRef = useRef<ActionType>();
  const columns: ProColumns<API.Attendance>[] = [
    {
      title: 'id',
      dataIndex: 'id',
      search: false,
    },
    {
      title: '员工账号',
      dataIndex: 'employeeAccount',
      copyable: true,
    },
    {
      title: '员工姓名',
      dataIndex: 'name',
      copyable: true,
    },
    {
      title: '考勤类别',
      dataIndex: 'attendanceType',
      valueType: 'select',
      valueEnum: {

        '正常': {text: '正常', status: 'Success'},
        '迟到': {text: '迟到', status: 'Error'},
        '缺勤': {text: '缺勤', status: 'Error'},
        '请假': {text: '请假', status: 'Default'},
      }
    },
    {
      title: '考勤日期',
      dataIndex: 'attendanceDate',
      valueType: 'date',
      search: false,
    },
  ];
  return (
    <PageContainer>
      <ProTable<API.Attendance>
        columns={columns}
        actionRef={actionRef}
        cardBordered
        request={async (params, sort, filter) => {
          console.log(sort, filter);
          const res = await getMyAttendanceUsingGET({...params});
          if (res) {
            return {
              data: res || [],
              success: true,
              total: res?.data?.length || 0,
            };
          } else {
            return {
              data: [],
              success: false,
              total: 0,
            };
          }
        }}
        editable={{
          type: 'multiple',
        }}
        columnsState={{
          persistenceKey: 'pro-table-singe-demos',
          persistenceType: 'localStorage',
          onChange(value) {
            console.log('value: ', value);
          },
        }}
        rowKey="id"
        search={{
          labelWidth: 'auto',
        }}
        form={{
          // 由于配置了 transform，提交的参与与定义的不同这里需要转化一下
          syncToUrl: (values, type) => {
            if (type === 'get') {
              return {
                ...values,
                created_at: [values.startTime, values.endTime],
              };
            }
            return values;
          },
        }}
        pagination={{
          pageSize: 5,
          onChange: (page) => console.log(page),
        }}
        dateFormatter="string"
        headerTitle="我的考勤"
      />
    </PageContainer>
  );
};

export default TableList;
