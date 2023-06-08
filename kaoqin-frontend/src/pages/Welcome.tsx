import {PageContainer, ProCard, StatisticCard} from '@ant-design/pro-components';
import RcResizeObserver from 'rc-resize-observer';
import React, {useEffect, useState} from 'react';
import AttendanceTypePie from "@/pages/charts/dashboard/AttendanceType/AttendanceType";
import EmployeesRadar from "@/pages/charts/dashboard/Employees/EmployeesRadar";

const Welcome: React.FC = () => {
  const [responsive, setResponsive] = useState(false);
  const [data, setData] = useState({});

  useEffect(() => {
    asyncFetch();
  }, []);

  const asyncFetch = () => {
    fetch('http://localhost:8080/api/charts/getChartsData')
      .then((response) => response.json())
      .then((json) => setData(json))
      .catch((error) => {
        console.log('fetch data failed', error);
      });
  };
  if (!Object.keys(data).length) {
    return null;
  }
  return (
    <PageContainer title={'报表中心'}>
      <RcResizeObserver
        key="resize-observer"
        onResize={(offset) => {
          setResponsive(offset.width < 596);
        }}
      >
        <ProCard
          title="数据概览"
          extra={new Date().toLocaleDateString()}
          split={responsive ? 'horizontal' : 'vertical'}
          headerBordered
          bordered
        >
          <ProCard split="horizontal">
            <ProCard split="horizontal">
              <ProCard split="vertical">
                <StatisticCard
                  statistic={{
                    title: '员工总数',
                    value: data.data.employeeCount,
                  }}
                />
                <StatisticCard
                  statistic={{
                    title: '部门总数',
                    value: data.data.deptCount,
                  }}
                />
              </ProCard>
            </ProCard>
            <StatisticCard
              title="本月考勤类型汇总"
              chart={
                <AttendanceTypePie/>
              }
            />
          </ProCard>
          <ProCard split="horizontal">
            <StatisticCard
              title="各部门员工数对比"
              chart={
                <EmployeesRadar/>
              }
            />
          </ProCard>


        </ProCard>
      </RcResizeObserver>
    </PageContainer>
  );
};

export default Welcome;
