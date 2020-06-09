const database = require('../../../data')
const crypto = require('crypto');
const moment = require('moment')

require('moment-timezone')
moment.tz.setDefault('Asia/Seoul')
//1분 * 1시간 * 24시간 * 3일 * 60 * 24
let expireDay = 60000 * 3

exports.check = (req, res) => {
    console.log('check ID duplication')
    var user_id = req.body.user_id

    database.User.findAll({
        where: {
            user_id: user_id
        }
    }).then( (results) => {
        if(!results.length) {
            console.log(user_id, 'is not duplicated')
            res.status(200).json({ result : "no duplication" })
        }
        else {
            console.log(user_id, 'is duplicated')
            res.status(404).json({ result : "duplication" })
        }
    }).catch( () => res.status(404).json({ result: "Undefiend error" }))
}

exports.signup = (req, res) => {
    console.log('Signup')

    var body = req.body
    var user_id = body.user_id
    var pwd = body.pwd
    var department = body.department
    var grade = body.grade
    var name = body.name

    console.log(body)

    var date = moment().format('YYYY-MM-DD HH:mm:ss')

    crypto.randomBytes(64, (err, buf) => {
        crypto.pbkdf2(pwd, buf.toString('base64'), 100000, 64, 'sha512', (err, key) => {
            pwd = key.toString('base64')
            salt = buf.toString('base64')

            database.User.create({
                user_id: user_id,
                pwd: pwd,
                salt: salt,
                grade: grade,
                name: name,
                department: department,
                signup_time: date

            }).then( () => {
                console.log(user_id, 'successed to sign up')
                res.status(200).json({ result : "Success" })
            }).catch( (err) => {
                console.log(err)
                console.log(user_id, 'failed to sign up')
                res.status(404).json({ result: "Undefined error" })
            })            
        })
    })
}

exports.login = (req, res) => {
    console.log('Login')

    var login_id = req.body.user_id
    var login_pwd = req.body.pwd

    database.User.findOne({
        where: {
            user_id: login_id
        }
    }).then( (result) => {
        crypto.randomBytes(64, (err, buf) => {
            crypto.pbkdf2(login_pwd, result.salt, 100000, 64, 'sha512', (err, key) => {
                if (result.pwd === key.toString('base64')) {
                    console.log(login_id, 'successed to login')
                    res.status(200).json({ result: 'Success' })

                } else {
                    console.log('Invalid password')
                    res.status(404).json({ result: 'Invalid password'})
                }
            })
        })

    }).catch( () => {
        console.log(login_id, 'is invalid User')
        res.status(404).json({ result : 'Invalid User' })
    })
    
}