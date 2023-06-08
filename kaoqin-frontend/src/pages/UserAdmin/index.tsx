import type {ActionType, ProColumns} from '@ant-design/pro-components';
import {PageContainer, ProTable} from '@ant-design/pro-components';
import {Avatar, Button, Input, message, Modal, Radio} from 'antd';
import React, {useRef, useState} from 'react';
import {
  addUserUsingPOST,
  deleteUserUsingPOST,
  listUserUsingGET,
  updateUserUsingPOST,
} from '@/services/kaoqin/userController';
import {PlusOutlined} from '@ant-design/icons';
import CreateModal from './components/CreateModal';
import UpdateModal from './components/UpdateModal';

const TableList: React.FC = () => {
  const [createModalVisible, handleModalVisible] = useState<boolean>(false);
  const [updateModalVisible, handleUpdateModalVisible] = useState<boolean>(false);
  const [showDetail] = useState<boolean>(false);
  const actionRef = useRef<ActionType>();
  const [currentRow, setCurrentRow] = useState<API.UserVO>();

  const handleAdd = async (fields: API.UserRegisterRequest) => {
    const hide = message.loading('正在添加');
    try {
      await addUserUsingPOST({
        ...fields,
      });
      hide();
      message.success('创建成功');
      handleModalVisible(false);
      actionRef.current?.reload();
      return true;
    } catch (error: any) {
      hide();
      message.error('创建失败，' + error.message);
      return false;
    }
  };

  const handleRemove = async (record: API.UserVO) => {
    Modal.confirm({
      title: '确认删除？',
      content: '删除后不可恢复',
      okText: '确认',
      cancelText: '取消',
      onOk: async () => {
        const hide = message.loading('正在删除');
        if (!record) return true;

        try {
          await deleteUserUsingPOST({
            id: record.id,
          });
          hide();
          message.success('删除成功，即将刷新');
          actionRef.current?.reload();
          return true;
        } catch (error: any) {
          hide();
          message.error('删除失败，请重试');
          return false;
        }
      },
    });
  };

  const handleUpdate = async (fields: API.UserUpdateRequest) => {
    if (!currentRow) {
      return;
    }
    const hide = message.loading('修改中');
    try {
      await updateUserUsingPOST({
        id: currentRow.id,
        ...fields,
      });
      hide();
      message.success('操作成功');
      return true;
    } catch (error: any) {
      hide();
      message.error('操作失败，' + error.message);
      return false;
    }
  };

  const columns: ProColumns<API.UserVO>[] = [
    {
      title: 'id',
      dataIndex: 'id',
      search: false,
    },
    {
      title: '员工账号',
      dataIndex: 'employeeAccount',
      search: false,
    },
    {
      title: '用户名',
      dataIndex: 'userName',
      copyable: true,
      search: false,
    },
    {
      title: '用户账户',
      dataIndex: 'userAccount',
      copyable: true,
    },
    {
      title: '头像',
      dataIndex: 'userAvatar',
      search: false,
      render: (_, record) => (
        <div>
          {/*<Image src={record.userAvatar} width={50} height={50}/>*/}
          <Avatar size="large" src={record.userAvatar}/>
        </div>
      ),
    },
    {
      title: '性别',
      dataIndex: 'gender',
      valueType: 'select',
      valueEnum: {
        '0': {text: '男'},
        '1': {text: '女'},
      },
    },
    {
      title: '角色',
      dataIndex: 'userRole',
      valueType: 'select',
      valueEnum: {
        user: {text: '普通用户', status: 'Default'},
        admin: {text: '管理员', status: 'Success'},
      },
    },
    {
      title: '创建时间',
      dataIndex: 'createTime',
      valueType: 'dateTime',
      search: false,
    },
    {
      title: '更新时间',
      dataIndex: 'updateTime',
      valueType: 'dateTime',
      search: false,
    },
    {
      title: '操作',
      dataIndex: 'option',
      valueType: 'option',
      render: (_, record) => [
        <a
          key="config"
          onClick={() => {
            handleUpdateModalVisible(true);
            setCurrentRow(record);
          }}
        >
          修改
        </a>,
        <Button
          type="text"
          key="config"
          danger
          onClick={() => {
            handleRemove(record);
          }}
        >
          删除
        </Button>,
      ],
    },
  ];

  const addUserColumns: ProColumns<API.UserAddRequest>[] = [
    {
      title: '用户账户',
      dataIndex: 'userAccount',
      formItemProps: {
        rules: [
          {
            required: true,
            message: '请输入账号',
          },
          {
            min: 4,
            message: '账号不能小于4位',
          },
        ],
      },
    },
    {
      title: '员工账号',
      dataIndex: 'employeeAccount',
      formItemProps: {
        rules: [
          {
            required: true,
            message: '请输入员工账号',
          },
        ],
      },
    },
    {
      title: '用户名',
      dataIndex: 'userName',
      formItemProps: {
        rules: [
          {
            required: true,
            message: '请输入用户名',
          },
        ],
      },
    },
    {
      title: '密码',
      dataIndex: 'userPassword',
      formItemProps: {
        rules: [
          {
            required: true,
            message: '请输入密码',
          },
          {
            min: 8,
            message: '密码长度不能少于8位',
          },
        ],
      },
      renderFormItem: () => <Input.Password/>,
    },
    {
      title: '性别',
      dataIndex: 'gender',
      formItemProps: {
        rules: [
          {
            required: true,
            message: '请选择性别',
          },
        ],
      },
      valueType: 'select',
      valueEnum: {
        0: '男',
        1: '女',
      },
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      renderFormItem: (item, {value, onChange}, form) => (
        <Radio.Group value={form.getFieldValue(item.dataIndex)}>
          <Radio value={0}>男</Radio>
          <Radio value={1}>女</Radio>
        </Radio.Group>
      ),
    },
    {
      title: '角色',
      dataIndex: 'userRole',
      valueType: 'select',
      formItemProps: {
        rules: [
          {
            required: true,
            message: '请选择角色',
          },
        ],
      },
      valueEnum: {
        user: {text: '普通用户', status: 'Default'},
        admin: {text: '管理员', status: 'Success'},
      },
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      renderFormItem: (item, {value, onChange}, form) => (
        <Radio.Group value={form.getFieldValue(item.dataIndex)}>
          <Radio value={'user'}>普通用户</Radio>
          <Radio value={'admin'}>管理员</Radio>
        </Radio.Group>
      ),
    },
  ];

  const updateUserColumns: ProColumns<API.UserUpdateRequest>[] = [
    {
      title: '用户账户',
      dataIndex: 'userAccount',
    },
    {
      title: '用户名',
      dataIndex: 'userName',
    },
    {
      title: '头像',
      dataIndex: 'userAvatar',
    },
    {
      title: '密码',
      dataIndex: 'userPassword',
      formItemProps: {
        rules: [
          {
            min: 8,
            message: '密码长度不能少于8位',
          },
        ],
      },
      renderFormItem: () => <Input.Password/>,
    },
    {
      title: '性别',
      dataIndex: 'gender',
      valueType: 'select',
      valueEnum: {
        0: '男',
        1: '女',
      },
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      renderFormItem: (item, {value, onChange}, form) => (
        <Radio.Group value={form.getFieldValue(item.dataIndex)}>
          <Radio value={0}>男</Radio>
          <Radio value={1}>女</Radio>
        </Radio.Group>
      ),
    },
    {
      title: '角色',
      dataIndex: 'userRole',
      valueType: 'select',
      valueEnum: {
        user: {text: '普通用户', status: 'Default'},
        admin: {text: '管理员', status: 'Success'},
      },
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      renderFormItem: (item, {value, onChange}, form) => (
        <Radio.Group value={form.getFieldValue(item.dataIndex)}>
          <Radio value={'user'}>普通用户</Radio>
          <Radio value={'admin'}>管理员</Radio>
        </Radio.Group>
      ),
    },
  ];
  return (
    <PageContainer>
      <ProTable<API.UserVO>
        columns={columns}
        actionRef={actionRef}
        cardBordered
        request={async (params, sort, filter) => {
          console.log(sort, filter);
          const res = await listUserUsingGET({...params});
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
        options={{
          setting: {
            listsHeight: 400,
          },
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
        headerTitle="用户管理"
        toolBarRender={() => [
          <Button
            key="button"
            icon={<PlusOutlined/>}
            type="primary"
            onClick={() => {
              handleModalVisible(true);
            }}
          >
            新建
          </Button>,
        ]}
      />
      <UpdateModal
        columns={updateUserColumns}
        onSubmit={async (value) => {
          const success = await handleUpdate(value);
          if (success) {
            handleUpdateModalVisible(false);
            setCurrentRow(undefined);
            if (actionRef.current) {
              actionRef.current.reload();
            }
          }
        }}
        onCancel={() => {
          handleUpdateModalVisible(false);
          if (!showDetail) {
            setCurrentRow(undefined);
          }
        }}
        visible={updateModalVisible}
        values={currentRow || {}}
      />

      <CreateModal
        columns={addUserColumns}
        onCancel={() => {
          handleModalVisible(false);
        }}
        onSubmit={(values) => {
          handleAdd(values);
        }}
        visible={createModalVisible}
      />
    </PageContainer>
  );
};

export default TableList;
