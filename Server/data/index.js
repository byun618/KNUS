const path = require('path')
var Sequelize = require('sequelize')

const env = process.env.MODE_ENV || 'development'
const config = require(path.join(__dirname, '..', 'bin', 'config.json'))[env]
var db = {}

var sequelize = new Sequelize(config.database, config.username, config.password, config)

db.sequelize = sequelize
db.Sequelize = Sequelize

db.User = require('./table/user')(sequelize, Sequelize)
db.Chat = require('./table/chat')(sequelize, Sequelize)
db.Board = require('./table/board')(sequelize, Sequelize)
db.Comment = require('./table/comment')(sequelize, Sequelize)

//db.User.hasOne(db.Coin, {as: 'fk_user', foreignKey: 'user_id'})

module.exports = db