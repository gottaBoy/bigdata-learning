# bigdata-learning

## zookeeper-learning

## kafka-learning

## hbase-learning

## elasticsearch-learning

## hadoop-learning

## spark-learing

## flink-learning

## flinkcdc-learning

## 机器学习
Spark MLib
Parameter Server
TensorFlow

### Spark MLib
ALS
MLP
DAG （Directed Acyclic Graph，有向无环图
job -> stage -> task
Random Forest（随机森林）
GBDT gradient boosting decision tree


### TensorFlow、PyTorch
RNN、LSTM

### Parameter Server

$$F(w) = \sum_{i=0}^N l(x_i, y_i, w) + \Omega(w)$$

Parameter Server分为两大部分：
服务器节点组(server group)和多个工作节点组(worker group)。资源管理中心(resource manager)负责总体的资源分配调度。
服务器节点组内部包含多个服务器节点(server node)，每个服务器节点负责维护一部分参数，服务器管理中心(server manager)负责维护和分配server资源。每个工作节点组对应一个Application（即一个模型训练任务），工作节点组之间，以及工作节点组内部的任务节点之间并不通信，任务节点只与server通信

Parameter Server仅仅是一个管理并行训练梯度的权重平台，并不涉及具体的模型实现，因此Parameter Server往往作为MXNet、TensorFlow的一个组件，要想具体实现一个机器学习模型，还需要依赖通用的、综合性的机器学习平台。

### TensorFlow为代表的机器学习平台的工作原理
tensor
pooling
active function
sigmoid

x w b
MatMul Add ReLU

### Embedding
PMML的全称是“预测模型标记语言”(Predictive Model Markup Language，PMML)，是一种通用的以XML的形式表示不同模型结构参数的标记语言。在模型上线的过程中，PMML经常作为中间媒介连接离线训练平台和线上预测平台

Scikit-learn、TensorFlow

### 推荐系统的评估问题
从离线评估到线上测试，从多个层级探讨推荐系统评估的方法和指标，具体包括下面内容：
- (1)离线评估的方法和指标。
- (2)离线仿真评估方法——Replay（重播评估法）。
- (3)线上A/B测试方法和线上评估指标。
- (4)快速线上评估测试方法——Interleaving（间隔插值测试法）

CTR(Click Through Rate)
